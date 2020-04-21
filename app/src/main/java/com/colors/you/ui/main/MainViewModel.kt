package com.colors.you.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colors.you.Either
import com.colors.you.domain.GetNewColorUseCase
import com.colors.you.domain.GetNewNameUseCase
import com.colors.you.domain.GetOldOrNewColorUseCase
import com.colors.you.domain.GetOldOrNewNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getOldOrNewColorUseCase: GetOldOrNewColorUseCase,
    private val getNewColorUseCase: GetNewColorUseCase,
    private val getNewNameUseCase: GetNewNameUseCase,
    private val getOldOrNewNameUseCase: GetOldOrNewNameUseCase
) : ViewModel() {

    val error: MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val colorCode: MutableLiveData<String> = MutableLiveData()
    val colorName: MutableLiveData<String> = MutableLiveData()

    fun getOldColor() {
        colorCode.value = getOldOrNewColorUseCase.run()

        isLoading.value = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = getOldOrNewNameUseCase.run()
                result.either({
                    //Do something with the error
                    Log.e("Error:", it.message)
                    viewModelScope.launch {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            error.value = it.message
                        }
                    }
                    Either.Left(it)
                }, {
                    viewModelScope.launch {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            colorName.value = it
                        }
                    }
                    Either.Right(it)
                })
            }

        }

    }

    fun getRandom() {
        colorCode.value = getNewColorUseCase.run()

        isLoading.value = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = getNewNameUseCase.run()
                result.either({
                    //Do something with the error
                    Log.e("Error:", it.message)
                    viewModelScope.launch {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            error.value = it.message
                        }
                    }
                    Either.Left(it)
                }, {
                    viewModelScope.launch {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            colorName.value = it
                        }
                    }
                    Either.Right(it)
                })
            }

        }
    }
}
