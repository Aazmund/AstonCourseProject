package com.example.astoncourseproject.data.dto.character

import com.google.gson.annotations.SerializedName

data class CharactersListDTO(
    @SerializedName("results")
    val result: List<CharacterByIdDTO>
)