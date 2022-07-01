package com.example.astoncourseproject.data.dto.episode

import com.example.astoncourseproject.data.entities.Episode
import com.google.gson.annotations.SerializedName

data class EpisodesListDTO(
    @SerializedName("results")
    val result: List<Episode>
)