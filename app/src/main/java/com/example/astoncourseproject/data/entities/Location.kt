package com.example.astoncourseproject.data.entities

data class Location (
    var id: String,
    var name: String,
    var type: String,
    var dimension: String,
    val residents: List<String>
)