package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.EpisodesListService

class EpisodeListRepository constructor(private val retrofitService: EpisodesListService) {
    suspend fun getAllEpisode(page: Int) = retrofitService.getEpisodeList(page)
}