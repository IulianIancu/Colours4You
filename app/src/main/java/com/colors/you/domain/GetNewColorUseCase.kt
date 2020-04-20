package com.colors.you.domain

class GetNewColorUseCase(
    private val randomColorRepository: RandomColorRepository,
    private val cacheRepository: CacheRepository
) {

    fun run(): String {
        val color = randomColorRepository.getNewColor()
        cacheRepository.saveColor(color)
        return color
    }
}