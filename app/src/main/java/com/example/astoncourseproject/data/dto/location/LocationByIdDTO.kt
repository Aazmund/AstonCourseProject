package com.example.astoncourseproject.data.dto.location

data class LocationByIdDTO(
    var id: String,
    var name: String,
    var type: String,
    var dimension: String,
    val residents: List<String>
)