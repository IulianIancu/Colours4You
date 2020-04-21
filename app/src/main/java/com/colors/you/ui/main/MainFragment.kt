package com.colors.you.ui.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.colors.you.Constants.NAME_GENERATOR_URL
import com.colors.you.Constants.SHARED_PREFERENCES_STORE
import com.colors.you.R
import com.colors.you.gone
import com.colors.you.observe
import com.colors.you.repository.NameGeneratorAPI
import com.colors.you.visible
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var factory: MainViewModelFactory
    private val viewModel: MainViewModel by lazy { initViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val service = Retrofit.Builder()
            .baseUrl(NAME_GENERATOR_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        factory = MainViewModelFactory(
            requireContext().getSharedPreferences(
                SHARED_PREFERENCES_STORE,
                Context.MODE_PRIVATE
            ),
            service.create(NameGeneratorAPI::class.java)
        )
    }

    private fun initViewModel() =
        ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh.setOnClickListener {
            viewModel.getRandom()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.apply {
            observe(error, ::handleErrorMessage)
            observe(isLoading, ::handleLoadingsState)
            observe(colorCode, ::handleNewColor)
            observe(colorName, ::handleNewName)
        }
        viewModel.getOldColor()
    }

    private fun handleErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleLoadingsState(state: Boolean?) {
        when (state) {
            true -> {
                progress_bar.visible()
                refresh.gone()
                background.gone()
                message.gone()
            }
            false -> {
                progress_bar.gone()
                refresh.visible()
                background.visible()
                message.visible()
            }
        }

    }

    private fun handleNewColor(color: String?) {
        //If there is no color just wait
        if (color == null) return

        val regularHex = Color.parseColor(color)
        val invertedHex = getInvertedColor(regularHex)

        background.setBackgroundColor(regularHex)

        //This is jut me being extra and making it so the text should be readable even on random colours
        message.setTextColor(invertedHex)
    }

    private fun handleNewName(name: String?) {
        name?.let {
            message.text = it
        }
    }

    //A method for determining what the opposite color is
    private fun getInvertedColor(hexa: Int): Int {
        val hsv = FloatArray(3)
        Color.RGBToHSV(
            Color.red(hexa),
            Color.green(hexa),
            Color.blue(hexa),
            hsv
        )
        hsv[0] = (hsv[0] + 180) % 360
        return Color.HSVToColor(hsv)
    }

}
