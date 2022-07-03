package com.example.astoncourseproject.data.dao

import androidx.room.*
import com.example.astoncourseproject.data.entities.LocationEntity

@Dao
interface LocationDao {
    @Query("SELECT*FROM locations_table")
    fun getAllLocations(): List<LocationEntity>

    @Query("SELECT*FROM locations_table WHERE id=:id")
    fun getLocationById(id: String): LocationEntity

    @Query("SELECT*FROM locations_table WHERE id >= :start AND id <= :end")
    fun getLocationByPage(start: Int, end: Int): List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLocation(locationEntity: LocationEntity)

    @Delete
    fun deleteLocation(locationEntity: LocationEntity)
}