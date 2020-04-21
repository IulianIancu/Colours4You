package com.colors.you.repository

import com.colors.you.domain.RandomColorRepository
import kotlin.random.Random

class RandomColorRepositoryImpl : RandomColorRepository {

    override fun getNewColor(): String {
        var result = "#"
        for (index in 1..6) {
            result = result.plus(
                Random.nextInt(0, 16).run {
                    when {
                        this in 0..9 -> this.toString()
                        this == 10 -> "a"
                        this == 11 -> "b"
                        this == 12 -> "c"
                        this == 13 -> "d"
                        this == 14 -> "e"
                        this == 15 -> "f"
                        else -> throw IllegalArgumentException()
                    }
                }
            )
        }
        return result
    }
}