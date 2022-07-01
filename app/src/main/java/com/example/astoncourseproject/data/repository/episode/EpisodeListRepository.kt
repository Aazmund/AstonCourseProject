package com.example.astoncourseproject.data.repository.episode

import com.example.astoncourseproject.data.network.episode.EpisodesListService

class EpisodeListRepository constructor(private val retrofitService: EpisodesListService) {
    suspend fun getAllEpisode(page: Int) = retrofitService.getEpisodeList(page)
}