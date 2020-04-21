package com.colors.you.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.colors.you.domain.GetNewColorUseCase
import com.colors.you.domain.GetNewNameUseCase
import com.colors.you.domain.GetOldOrNewColorUseCase
import com.colors.you.domain.GetOldOrNewNameUseCase
import com.colors.you.repository.CacheRepositoryImpl
import com.colors.you.repository.NameGeneratorAPI
import com.colors.you.repository.RandomColorRepositoryImpl
import com.colors.you.repository.RandomNameRepositoryImpl
import retrofit2.Retrofit


class MainViewModelFactory(
    private val sharedPreferences: SharedPreferences,
    private val nameGenerator: NameGeneratorAPI
) : ViewModelProvider.NewInstanceFactory() {

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
                        ),
                        GetNewNameUseCase(
                            RandomNameRepositoryImpl(nameGenerator),
                            CacheRepositoryImpl(sharedPreferences)
                        ),
                        GetOldOrNewNameUseCase(
                            RandomNameRepositoryImpl(nameGenerator),
                            CacheRepositoryImpl(sharedPreferences)
                        )
                    )
                else -> throw IllegalArgumentException(
                    "Unknown ViewModel class: ${modelClass.name}"
                )
            }
        } as T

}