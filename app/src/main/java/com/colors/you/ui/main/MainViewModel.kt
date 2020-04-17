package com.colors.you.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colors.you.domain.RandomColorRepository

class MainViewModel(private val colorRepository: RandomColorRepository) : ViewModel() {

    val error: MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val colorCode: MutableLiveData<String> = MutableLiveData()

    fun getRandom() {
        colorCode.value = colorRepository.getColor()
    }
}
