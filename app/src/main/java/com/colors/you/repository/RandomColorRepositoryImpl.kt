package com.colors.you.repository

import com.colors.you.domain.RandomColorRepository
import kotlin.random.Random

class RandomColorRepositoryImpl : RandomColorRepository {
    override fun getColor(): String {
        return when (Random.nextBoolean()) {
            true -> "#ff2244"
            false -> "#a81567"
        }
    }
}