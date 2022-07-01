package com.example.astoncourseproject.data.dto.character

import com.example.astoncourseproject.data.entities.Character
import com.google.gson.annotations.SerializedName

data class CharactersListDTO(
    @SerializedName("results")
    val result: List<Character>
)