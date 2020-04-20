package com.colors.you.domain

import com.colors.you.Either

class GetOldOrNewNameUseCase(private val randomNameRepository: RandomNameRepository) {

    fun run():Either<Throwable,String>{
        return  randomNameRepository.getOldOrNewName()
    }
}