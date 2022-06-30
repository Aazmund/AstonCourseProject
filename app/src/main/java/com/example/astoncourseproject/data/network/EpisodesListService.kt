package com.example.astoncourseproject.data.network

import com.example.astoncourseproject.data.dto.EpisodeDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesListService {
    @GET("episode")
    suspend fun getEpisodeList(@Query("page") page: Int): Response<EpisodeDTO>

    companion object {
        var retrofitService: EpisodesListService? = null

        fun getInstance() : EpisodesListService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(EpisodesListService::class.java)
            }
            return retrofitService!!
        }
    }
}