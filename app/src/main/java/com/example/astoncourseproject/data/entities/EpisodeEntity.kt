package com.example.astoncourseproject.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes_table")
data class EpisodeEntity (
    @PrimaryKey
    var id: String,
    var name: String,
    var airDate: String,
    var episode: String,
    var characters: String
)