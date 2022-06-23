package com.example.astoncourseproject.entities

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class Character (
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Map<String, String>,
    val location: Map<String, String>,
    val image: String,
    var imageBitmap: Bitmap,
    val episode: List<String>
)

data class CharacterResponse(
    @SerializedName("results")
    val results: List<Character>
)