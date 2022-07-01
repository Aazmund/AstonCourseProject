package com.example.astoncourseproject.data.repository.location

import com.example.astoncourseproject.data.network.location.LocationMultiIdService

class LocationMultiIdRepository constructor(private val retrofitService: LocationMultiIdService) {
    suspend fun getLocationMultiId(id: String) = retrofitService.getLocationMultiId(id)
}