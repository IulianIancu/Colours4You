package com.colors.you.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colors.you.domain.GetNewColorUseCase
import com.colors.you.domain.GetOldOrNewColorUseCase

class MainViewModel(
    private val getOldOrNewColorUseCase: GetOldOrNewColorUseCase,
    private val getNewColorUseCase: GetNewColorUseCase
) : ViewModel() {

    val error: MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val colorCode: MutableLiveData<String> = MutableLiveData()

    fun getOldColor() {
        colorCode.value = getOldOrNewColorUseCase.run()
    }

    fun getRandom() {
        colorCode.value = getNewColorUseCase.run()
    }
}
