package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.EpisodesListService

class EpisodeRepository constructor(private val retrofitService: EpisodesListService) {
    suspend fun getAllEpisode() = retrofitService.getEpisodeList()
}