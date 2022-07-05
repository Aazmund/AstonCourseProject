package com.example.astoncourseproject.data.repository.location

import com.example.astoncourseproject.data.network.location.LocationFilterService

class LocationsFilterListRepository constructor(private val retrofitService: LocationFilterService) {
    suspend fun getFilterLocation(map: Map<String, String>) = retrofitService.getLocationsWithFilters(map)
}