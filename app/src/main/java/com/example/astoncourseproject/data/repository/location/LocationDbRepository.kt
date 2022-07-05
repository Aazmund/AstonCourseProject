package com.example.astoncourseproject.data.repository.location

import android.content.Context
import com.example.astoncourseproject.data.dao.LocationDao
import com.example.astoncourseproject.data.database.AppDatabase
import com.example.astoncourseproject.data.entities.LocationEntity
import com.example.astoncourseproject.domain.models.Location
import java.lang.reflect.Executable

class LocationDbRepository(context: Context) {
    private var db: LocationDao = AppDatabase.getInstance(context)?.locationDao()!!

    fun getAllLocations() = db.getAllLocations()

    fun getLocationByPage(page: Int): List<Location>{
        val list = mutableListOf<Location>()
        for (obj in db.getLocationByPage((page - 1) * 20 + 1, (page - 1) * 20 + 20)){
            val location = Location(
                obj.id,
                obj.name,
                obj.type,
                obj.dimension,
                obj.residents.split(",").toList()
            )
            list.add(location)
        }
        return list
    }

    fun getLocationById(id: String): Location {
        var location: Location
        try{
            location = Location(
                db.getLocationById(id).id,
                db.getLocationById(id).name,
                db.getLocationById(id).type,
                db.getLocationById(id).dimension,
                db.getLocationById(id).residents.split(",").toList()
            )
        }catch (e: Exception){
            location = Location(
                "no local data",
                "no local data",
                "no local data",
                "no local data",
                listOf("no local data", "no local data")
            )
        }
        return location
    }

    fun getLocationByName(name: String): List<Location>{
        val list = mutableListOf<Location>()
        try {
            for (obj in db.getLocationByName(name)){
                val location = Location(
                    obj.id,
                    obj.name,
                    obj.type,
                    obj.dimension,
                    obj.residents.split(",").toList()
                )
                list.add(location)
            }
        } catch (e: Exception){}
        return list
    }

    private fun addLocation(location: Location) {
        db.addLocation(
            LocationEntity(
                location.id,
                location.name,
                location.type,
                location.dimension,
                location.residents.joinToString()
            )
        )
    }



    fun addLocationsList(list: List<Location>) {
        for (obj in list) {
            addLocation(obj)
        }
    }

    fun deleteLocations(location: Location) {
        db.deleteLocation(
            LocationEntity(
                location.id,
                location.name,
                location.type,
                location.dimension,
                location.residents.joinToString()
            )
        )
    }
}