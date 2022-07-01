package com.example.astoncourseproject.data.entities

import com.google.gson.annotations.SerializedName

data class Episode (
    var id: String,
    var name: String,
    @SerializedName("air_date")
    var airDate: String,
    var episode: String,
    var characters: List<String>
)