package com.colors.you.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.colors.you.repository.RandomColorRepositoryImpl


class MainViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(RandomColorRepositoryImpl())
                else -> throw IllegalArgumentException(
                    "Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}