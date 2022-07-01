package com.example.astoncourseproject.data.repository.location

import com.example.astoncourseproject.data.network.location.LocationsListService

class LocationListRepository constructor(private val retrofitService: LocationsListService) {
    suspend fun getAllLocations(page: Int) = retrofitService.getLocationList(page)
}