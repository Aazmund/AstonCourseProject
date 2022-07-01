package com.example.astoncourseproject.data.repository.episode

import com.example.astoncourseproject.data.network.episode.EpisodeMultiIdService

class EpisodeMultiIdRepository constructor(private val retrofitService: EpisodeMultiIdService) {
    suspend fun getEpisodeMultiId(id: List<String>) = retrofitService.getEpisodeMultiId(id)
}