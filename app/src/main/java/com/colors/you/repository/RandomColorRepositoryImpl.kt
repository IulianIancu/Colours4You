package com.colors.you.repository

import android.content.SharedPreferences
import com.colors.you.domain.RandomColorRepository
import com.colors.you.use
import java.lang.IllegalArgumentException
import kotlin.random.Random

class RandomColorRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : RandomColorRepository {

    companion object {
        //TODO this probably shouldn't be here. A constant file?
        const val SHARED_PREFERENCES_STORE = "application_preferences"
        const val COLOR_ID = "last_color"
    }

    override fun getColor(): String {
        return sharedPreferences.getString(COLOR_ID, null)?.takeIf { it.isNotBlank() }
            ?: getNewColor()
    }

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
        sharedPreferences.use {
            putString(COLOR_ID, result)
        }
        return result
    }
}