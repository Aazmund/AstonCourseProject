package com.example.astoncourseproject.data.network

import com.example.astoncourseproject.data.dto.EpisodeDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitEpisodeService {
    @GET("episode")
    suspend fun getEpisodeList(): Response<EpisodeDTO>

    companion object {
        var retrofitService: RetrofitEpisodeService? = null

        fun getInstance() : RetrofitEpisodeService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitEpisodeService::class.java)
            }
            return retrofitService!!
        }
    }
}