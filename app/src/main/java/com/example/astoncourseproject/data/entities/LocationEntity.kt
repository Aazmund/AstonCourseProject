package com.example.astoncourseproject.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations_table")
data class LocationEntity (
    @PrimaryKey
    var id: String,
    var name: String,
    var type: String,
    var dimension: String,
    val residents: String
)