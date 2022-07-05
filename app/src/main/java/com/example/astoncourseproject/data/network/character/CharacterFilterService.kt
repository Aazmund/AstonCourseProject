package com.example.astoncourseproject.data.network.character

import com.example.astoncourseproject.data.dto.character.CharactersListDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CharacterFilterService {
    @GET("character")
    suspend fun getCharacterWithFilters(@QueryMap filters:Map<String, String>): Response<CharactersListDTO>

    companion object {
        var retrofitService: CharacterFilterService? = null

        fun getInstance(): CharacterFilterService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CharacterFilterService::class.java)
            }
            return retrofitService!!
        }
    }
}