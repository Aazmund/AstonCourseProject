package com.example.astoncourseproject.domain.usecase.location

import com.example.astoncourseproject.data.repository.location.LocationsFilterListRepository
import com.example.astoncourseproject.domain.models.Location

class GetLocationFilterListUseCase constructor(private val repository: LocationsFilterListRepository) {
    suspend fun execute(map: Map<String, String>): List<Location> {
        val list = mutableListOf<Location>()
        val response = repository.getFilterLocation(map)

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