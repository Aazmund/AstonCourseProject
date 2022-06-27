package com.example.astoncourseproject.data.dto

import com.example.astoncourseproject.data.entities.Location
import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("results")
    val result: List<Location>
)