package com.example.astoncourseproject.data.repository.location

import com.example.astoncourseproject.data.network.location.LocationByIdService

class LocationByIdRepository constructor(private val retrofitService: LocationByIdService) {
    suspend fun getLocationById(id: String) = retrofitService.getLocationById(id)
}