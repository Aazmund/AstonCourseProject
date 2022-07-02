package com.example.astoncourseproject.domain.usecase.location

import com.example.astoncourseproject.data.network.location.LocationMultiIdService
import com.example.astoncourseproject.domain.models.Location

class GetLocationMultiIdUseCase constructor(private val repository: LocationMultiIdService) {

    suspend fun execute(id: String): List<Location> {
        val list = mutableListOf<Location>()
        val response = repository.getLocationMultiId(id)

        if (response.isSuccessful && response.body() != null) {
            for (obj in response.body()!!) {
                val location = Location(
                    id = obj.id,
                    name = obj.name,
                    type = obj.type,
                    dimension = obj.dimension,
                    residents = obj.residents
                )
                list.add(location)
            }
        }
        return list
    }
}