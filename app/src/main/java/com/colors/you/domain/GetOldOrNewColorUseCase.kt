package com.colors.you.domain

class GetOldOrNewColorUseCase(
    private val randomColorRepository: RandomColorRepository,
    private val cacheRepository: CacheRepository
) {

    fun run(): String {
        cacheRepository.getColor()?.let { return it }

        val newColor = randomColorRepository.getNewColor()
        cacheRepository.saveColor(newColor)
        return newColor
    }
}