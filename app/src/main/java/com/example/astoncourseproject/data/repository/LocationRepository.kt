package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.RetrofitLocationService

class LocationRepository constructor(private val retrofitService: RetrofitLocationService) {
    suspend fun getAllLocations() = retrofitService.getLocationList()
}