package com.example.astoncourseproject.data.network.episode

import com.example.astoncourseproject.data.dto.episode.EpisodesListDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesListService {
    @GET("episode")
    suspend fun getEpisodeList(@Query("page") page: Int): Response<EpisodesListDTO>

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