package com.example.astoncourseproject.data.network.episode

import com.example.astoncourseproject.data.dto.episode.EpisodeByIdDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeByIdService {
    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: String): Response<EpisodeByIdDTO>

    companion object {
        var retrofitService: EpisodeByIdService? = null

        fun getInstance(): EpisodeByIdService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(EpisodeByIdService::class.java)
            }
            return retrofitService!!
        }
    }
}
