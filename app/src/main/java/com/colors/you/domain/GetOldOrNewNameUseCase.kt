package com.colors.you.domain

import com.colors.you.Either

class GetOldOrNewNameUseCase(
    private val randomNameRepository: RandomNameRepository,
    private val cacheRepository: CacheRepository
) {

    suspend fun run(): Either<Throwable, String> {
        cacheRepository.getName()?.takeIf { it.isNotBlank() }?.let { return Either.Right(it) }

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