package com.colors.you.domain

class GetOldOrNewColorUseCase(private val randomColorRepository: RandomColorRepository) {

    fun run(): String {
        return randomColorRepository.getColor()
    }
}