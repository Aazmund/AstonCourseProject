package com.example.astoncourseproject.domain.usecase.location

import com.example.astoncourseproject.data.repository.location.LocationListRepository
import com.example.astoncourseproject.domain.models.Location

class GetLocationListUseCase constructor(private val repository: LocationListRepository) {

    suspend fun execute(page: Int): List<Location> {
        val list = mutableListOf<Location>()
        val response = repository.getAllLocations(page)

        if (response.isSuccessful && response.body() != null) {
            for (obj in response.body()!!.result) {
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