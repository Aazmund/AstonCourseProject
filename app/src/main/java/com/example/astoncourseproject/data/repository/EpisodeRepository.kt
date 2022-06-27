package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.RetrofitEpisodeService

class EpisodeRepository constructor(private val retrofitService: RetrofitEpisodeService) {
    suspend fun getAllEpisode() = retrofitService.getEpisodeList()
}