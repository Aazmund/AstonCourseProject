package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.LocationByIdService

class LocationByIdRepository constructor(private val retrofitService: LocationByIdService) {
    suspend fun getLocationById(id: String) = retrofitService.getLocationById(id)
}