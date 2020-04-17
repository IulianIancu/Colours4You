package com.colors.you.domain

interface RandomColorRepository {

    fun getNewColor(): String
    fun getColor(): String
}