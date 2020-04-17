package com.colors.you.domain

class GetNewColorUseCase(private val randomColorRepository: RandomColorRepository) {

    fun run(): String {
        return randomColorRepository.getNewColor()
    }
}