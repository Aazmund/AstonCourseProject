package com.example.astoncourseproject.network

import com.example.astoncourseproject.entities.CharacterResponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("character")
    fun getCharacterList(): Call<CharacterResponse>
}