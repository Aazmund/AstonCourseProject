package com.example.astoncourseproject.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.astoncourseproject.data.entities.Character

interface CharacterByIdService {
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): Response<Character>

    companion object {
        var retrofitService: CharacterByIdService? = null

        fun getInstance(): CharacterByIdService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CharacterByIdService::class.java)
            }
            return retrofitService!!
        }
    }
}