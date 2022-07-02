package com.example.astoncourseproject.data.dto.episode

import com.google.gson.annotations.SerializedName

data class EpisodeByIdDTO(
    var id: String,
    var name: String,
    @SerializedName("air_date")
    var airDate: String,
    var episode: String,
    var characters: List<String>
)
