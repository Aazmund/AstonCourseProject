package com.example.astoncourseproject.data.network.location

import com.example.astoncourseproject.data.dto.location.LocationsListDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsListService {
    @GET("location")
    suspend fun getLocationList(@Query("page") page: Int): Response<LocationsListDTO>

    companion object {
        var retrofitService: LocationsListService? = null

        fun getInstance(): LocationsListService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(LocationsListService::class.java)
            }
            return retrofitService!!
        }
    }
}