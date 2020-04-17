package com.colors.you.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {

    val error: MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val colorCode: MutableLiveData<String> = MutableLiveData()

    fun getRandom() {
        colorCode.value = when (Random.nextBoolean()) {
            true -> "#ff2244"
            false -> "#a81567"
        }

    }
}
