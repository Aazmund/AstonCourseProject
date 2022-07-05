package com.example.astoncourseproject.data.repository.episode

import com.example.astoncourseproject.data.network.episode.EpisodeFilterService

class EpisodesFilterListRepository constructor(private val retrofitService: EpisodeFilterService) {
    suspend fun getFilterEpisode(map: Map<String, String>) = retrofitService.getEpisodeNameWithFilters(map)
}