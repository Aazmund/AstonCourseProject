package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.LocationsListService

class LocationListRepository constructor(private val retrofitService: LocationsListService) {
    suspend fun getAllLocations(page: Int) = retrofitService.getLocationList(page)
}