package com.example.astoncourseproject.data.dto.location

import com.example.astoncourseproject.data.entities.Location
import com.google.gson.annotations.SerializedName

data class LocationsListDTO(
    @SerializedName("results")
    val result: List<Location>
)