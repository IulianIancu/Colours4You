package com.colors.you.domain

import com.colors.you.Either

class GetNewNameUseCase(
    private val randomNameRepository: RandomNameRepository,
    private val cacheRepository: CacheRepository
) {

    fun run(): Either<Throwable, String> {
        val name = randomNameRepository.getNewName()
        name.either({
            //Do something with the error if you'd like
            Either.Left(it)
        }, {
            cacheRepository.saveName(it)
            Either.Right(it)
        })

        return name
    }
}