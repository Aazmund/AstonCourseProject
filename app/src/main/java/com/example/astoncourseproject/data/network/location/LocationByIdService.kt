package com.example.astoncourseproject.data.network.location

import com.example.astoncourseproject.data.dto.location.LocationByIdDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationByIdService {
    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: String): Response<LocationByIdDTO>

    companion object {
        var retrofitService: LocationByIdService? = null

        fun getInstance(): LocationByIdService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(LocationByIdService::class.java)
            }
            return retrofitService!!
        }
    }
}