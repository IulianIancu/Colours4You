package com.colors.you.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.colors.you.R
import com.colors.you.observe
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val factory = MainViewModelFactory()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refresh.setOnClickListener {
            viewModel.getRandom()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.apply {
            observe(isLoading, ::handleLoadingsState)
            observe(colorCode, ::handleNewColor)
        }
        viewModel.getRandom()
    }

    private fun handleLoadingsState(state: Boolean?) {
        //TODO Do some loading animation?
    }

    private fun handleNewColor(color: String?) {
        //If there is no color just wait
        if (color == null) return

        val regularHex = Color.parseColor(color)
        val invertedHex = getInvertedColor(regularHex)

        background.setBackgroundColor(regularHex)

        //This is jut me being extra and making it so the text should be readable
        message.setTextColor(invertedHex)
    }

    private fun getInvertedColor(hexa: Int): Int { //hexa = "#28cb43";
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
