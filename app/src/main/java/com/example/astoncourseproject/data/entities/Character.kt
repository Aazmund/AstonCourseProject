package com.example.astoncourseproject.data.entities

data class Character (
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Map<String, String>,
    val location: Map<String, String>,
    val image: String,
    val episode: List<String>
)