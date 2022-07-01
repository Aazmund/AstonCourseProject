package com.example.astoncourseproject.data.repository.episode

import com.example.astoncourseproject.data.network.episode.EpisodeByIdService

class EpisodeByIdRepository constructor(private val retrofitService: EpisodeByIdService) {
    suspend fun getEpisodeById(id: String) = retrofitService.getEpisodeById(id)
}