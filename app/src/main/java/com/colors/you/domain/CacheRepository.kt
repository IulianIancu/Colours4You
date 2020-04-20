package com.colors.you.domain

//
// Created by  on 20/04/2020.
//

interface CacheRepository {

    fun saveColor(color:String)
    fun getColor():String?

    fun saveName(name:String)
    fun getName():String?
}