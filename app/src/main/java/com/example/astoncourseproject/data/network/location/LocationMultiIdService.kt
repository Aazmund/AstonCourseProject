package com.example.astoncourseproject.data.network.location

import com.example.astoncourseproject.data.dto.location.LocationsByIdDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationMultiIdService {
    @GET("location")
    suspend fun getLocationMultiId(@Query("id") id: String): Response<LocationsByIdDTO>

    companion object {
        var retrofitService: LocationMultiIdService? = null

        fun getInstance(): LocationMultiIdService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(LocationMultiIdService::class.java)
            }
            return retrofitService!!
        }
    }
}