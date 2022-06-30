package com.example.astoncourseproject.data.dto

import com.example.astoncourseproject.data.entities.Character
import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("results")
    val result: List<Character>
)