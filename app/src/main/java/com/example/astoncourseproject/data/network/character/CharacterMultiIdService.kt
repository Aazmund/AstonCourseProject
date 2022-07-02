package com.example.astoncourseproject.data.network.character

import com.example.astoncourseproject.data.dto.character.CharacterByIdDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterMultiIdService {
    @GET("character/{id}")
    suspend fun getCharacterMultiId(
        @Path("id", encoded = true) id: List<String>
    ): Response<List<CharacterByIdDTO>>

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