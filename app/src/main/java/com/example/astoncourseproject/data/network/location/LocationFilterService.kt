package com.example.astoncourseproject.data.network.location

import com.example.astoncourseproject.data.dto.location.LocationsListDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface LocationFilterService {
    @GET("location")
    suspend fun getLocationsWithFilters(@QueryMap filters: Map<String, String>): Response<LocationsListDTO>

    companion object {
        var retrofitService: LocationFilterService? = null

        fun getInstance(): LocationFilterService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(LocationFilterService::class.java)
            }
            return retrofitService!!
        }
    }
}