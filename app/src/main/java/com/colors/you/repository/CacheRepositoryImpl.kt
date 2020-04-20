package com.colors.you.repository

import android.content.SharedPreferences
import com.colors.you.Constants
import com.colors.you.domain.CacheRepository
import com.colors.you.use

//
// Created by  on 20/04/2020.
//

class CacheRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : CacheRepository {
    override fun saveColor(color: String) {
        sharedPreferences.use {
            putString(Constants.COLOR_ID, color)
        }
    }

    override fun getColor(): String? {
        return sharedPreferences.getString(Constants.COLOR_ID, null)?.takeIf { it.isNotBlank() }
    }

    override fun saveName(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getName(): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}