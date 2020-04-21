package com.colors.you.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NameGeneratorAPI {

    @GET("word")
    fun getRandomName(@Query("number") wordCount: Int): Call<List<String>>
}