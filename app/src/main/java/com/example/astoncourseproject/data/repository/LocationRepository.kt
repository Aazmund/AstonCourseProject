package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.LocationsListService

class LocationRepository constructor(private val retrofitService: LocationsListService) {
    suspend fun getAllLocations() = retrofitService.getLocationList()
}