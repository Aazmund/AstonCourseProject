package com.example.astoncourseproject.domain.usecase.location

import com.example.astoncourseproject.data.repository.location.LocationByIdRepository
import com.example.astoncourseproject.domain.models.Location

class GetLocationByIdUseCase constructor(private val repository: LocationByIdRepository) {

    suspend fun execute(id: String): List<Location> {
        val list = mutableListOf<Location>()
        val response = repository.getLocationById(id)

        if (response.isSuccessful && response.body() != null) {
            val location = Location(
                id = response.body()!!.id,
                name = response.body()!!.name,
                type = response.body()!!.type,
                dimension = response.body()!!.dimension,
                residents = response.body()!!.residents
            )
            list.add(location)
        }
        return list
    }
}