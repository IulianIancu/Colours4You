package com.colors.you.domain

import com.colors.you.Either

interface RandomNameRepository {
    fun getNewName():Either<Throwable,String>
}