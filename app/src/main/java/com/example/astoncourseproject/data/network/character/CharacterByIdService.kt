package com.example.astoncourseproject.data.network.character

import com.example.astoncourseproject.data.dto.character.CharacterByIdDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterByIdService {
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): Response<CharacterByIdDTO>

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