package com.example.astoncourseproject.data.network.episode

import com.example.astoncourseproject.data.dto.episode.EpisodesListDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface EpisodeFilterService {
    @GET("episode")
    suspend fun getEpisodeNameWithFilters(@QueryMap filters: Map<String, String>): Response<EpisodesListDTO>

    companion object {
        var retrofitService: EpisodeFilterService? = null

        fun getInstance(): EpisodeFilterService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(EpisodeFilterService::class.java)
            }
            return retrofitService!!
        }
    }
}