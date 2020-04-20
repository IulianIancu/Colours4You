package com.colors.you.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.colors.you.domain.GetNewColorUseCase
import com.colors.you.domain.GetOldOrNewColorUseCase
import com.colors.you.repository.CacheRepositoryImpl
import com.colors.you.repository.RandomColorRepositoryImpl


class MainViewModelFactory(private val sharedPreferences: SharedPreferences) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(
                        GetOldOrNewColorUseCase(
                            RandomColorRepositoryImpl(),
                            CacheRepositoryImpl(sharedPreferences)
                        ),
                        GetNewColorUseCase(
                            RandomColorRepositoryImpl(),
                            CacheRepositoryImpl(sharedPreferences)
                        )
                    )
                else -> throw IllegalArgumentException(
                    "Unknown ViewModel class: ${modelClass.name}"
                )
            }
        } as T

}