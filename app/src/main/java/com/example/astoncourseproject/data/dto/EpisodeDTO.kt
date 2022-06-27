package com.example.astoncourseproject.data.dto

import com.example.astoncourseproject.data.entities.Episode
import com.google.gson.annotations.SerializedName

data class EpisodeDTO(
    @SerializedName("results")
    val result: List<Episode>
)