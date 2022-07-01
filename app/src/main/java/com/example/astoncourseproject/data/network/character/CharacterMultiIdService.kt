package com.example.astoncourseproject.data.network.character

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.astoncourseproject.data.entities.Character

interface CharacterMultiIdService {
    @GET("character/{id}")
    suspend fun getCharacterMultiId(@Path("id", encoded = true) id: List<String>): Response<List<Character>>

    companion object {
        var retrofitService: CharacterMultiIdService? = null

        fun getInstance(): CharacterMultiIdService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CharacterMultiIdService::class.java)
            }
            return retrofitService!!
        }
    }
}