package com.example.astoncourseproject.domain.usecase

import com.example.astoncourseproject.data.repository.LocationListRepository
import com.example.astoncourseproject.domain.models.Location

class GetLocationListUseCase constructor(private val repository: LocationListRepository) {

    private val list = mutableListOf<Location>()

    suspend fun execute(page: Int): List<Location> {
        list.clear()

        val response = repository.getAllLocations(page)

        if (response.isSuccessful && response.body() != null) {
            for (obj in response.body()!!.result) {
                val location = Location(
                    id = obj.id,
                    name = obj.name,
                    type = obj.type,
                    dimension = obj.dimension
                )
                list.add(location)
            }
        }
        return list
    }
}