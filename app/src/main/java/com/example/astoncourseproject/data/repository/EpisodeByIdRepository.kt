package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.EpisodeByIdService

class EpisodeByIdRepository constructor(private val retrofitService: EpisodeByIdService) {
    suspend fun getEpisodeById(id: String) = retrofitService.getEpisodeById(id)
}