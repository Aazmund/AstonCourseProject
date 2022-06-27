package com.example.astoncourseproject.domain.usecase

import com.example.astoncourseproject.data.repository.LocationRepository
import com.example.astoncourseproject.domain.models.Location

class GetLocationListUseCase constructor(private val repository: LocationRepository) {

    private val list = mutableListOf<Location>()

    suspend fun execute(): List<Location> {
        list.clear()

        val response = repository.getAllLocations()

        if (response.isSuccessful){
            for (obj in response.body()!!.result){
                val location = Location(
                    name = obj.name,
                    type = obj.type,
                    dimension = obj.dimension
                )
                list.add(location)
            }
        }
        println(list)
        return list
    }
}