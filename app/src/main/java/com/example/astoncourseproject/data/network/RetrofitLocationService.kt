package com.example.astoncourseproject.data.network

import com.example.astoncourseproject.data.dto.LocationDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitLocationService {
    @GET("location")
    suspend fun getLocationList(): Response<LocationDTO>

    companion object {
        var retrofitService: RetrofitLocationService? = null

        fun getInstance() : RetrofitLocationService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitLocationService::class.java)
            }
            return retrofitService!!
        }
    }
}