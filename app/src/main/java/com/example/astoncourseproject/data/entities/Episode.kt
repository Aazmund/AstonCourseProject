package com.example.astoncourseproject.data.entities

import com.google.gson.annotations.SerializedName

class Episode {
    var name: String = ""
    var episode: String = ""
    @SerializedName("air_date")
    var airDate: String = ""
}