package com.colors.you.ui.main

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.colors.you.R
import com.colors.you.observe
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
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
            observe(isLoading,::handleLoadingsState)
            observe(colorCode,::handleNewColor)
        }
        viewModel.getRandom()
    }

    private fun handleLoadingsState(state:Boolean?){
        //TODO Do some loading animation?
    }

    private fun handleNewColor(color:String?){
        val hex = Color.parseColor(color)
        background.setBackgroundColor(hex)
    }


}
