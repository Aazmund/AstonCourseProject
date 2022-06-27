package com.example.astoncourseproject.data.network

import com.example.astoncourseproject.data.dto.CharacterDTO
import com.example.astoncourseproject.data.entities.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitCharacterService {
    @GET("character")
    suspend fun getCharacterList(): Response<CharacterDTO>

    companion object {
        var retrofitService: RetrofitCharacterService? = null

        fun getInstance() : RetrofitCharacterService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitCharacterService::class.java)
            }
            return retrofitService!!
        }
    }
}