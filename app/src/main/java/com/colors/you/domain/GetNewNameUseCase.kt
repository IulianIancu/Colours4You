package com.colors.you.domain

import com.colors.you.Either

class GetNewNameUseCase(private val randomNameRepository: RandomNameRepository) {

    fun run(): Either<Throwable, String> {
        return  randomNameRepository.getNewName()
    }
}