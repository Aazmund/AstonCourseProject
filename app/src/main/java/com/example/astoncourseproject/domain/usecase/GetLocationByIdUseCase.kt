package com.example.astoncourseproject.domain.usecase

import com.example.astoncourseproject.data.repository.LocationByIdRepository
import com.example.astoncourseproject.domain.models.Location

class GetLocationByIdUseCase constructor(private val repository: LocationByIdRepository) {

    private val list = mutableListOf<Location>()

    suspend fun execute(id: String): List<Location> {
        val response = repository.getLocationById(id)

        if (response.isSuccessful) {
            val location = Location(
                id = response.body()!!.id,
                name = response.body()!!.name,
                type = response.body()!!.type,
                dimension = response.body()!!.dimension
            )
            list.add(location)
        }
        return list
    }
}