package com.example.astoncourseproject.data.dto.episode

import com.google.gson.annotations.SerializedName

data class EpisodesListDTO(
    @SerializedName("results")
    val result: List<EpisodeByIdDTO>
)