package com.example.astoncourseproject.data.network.episode

import com.example.astoncourseproject.data.entities.Episode
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeMultiIdService {
    @GET("episode/{id}")
    suspend fun getEpisodeMultiId(@Path("id", encoded = true) id: List<String>): Response<List<Episode>>

    companion object {
        var retrofitService: EpisodeMultiIdService? = null

        fun getInstance(): EpisodeMultiIdService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(EpisodeMultiIdService::class.java)
            }
            return retrofitService!!
        }
    }
}