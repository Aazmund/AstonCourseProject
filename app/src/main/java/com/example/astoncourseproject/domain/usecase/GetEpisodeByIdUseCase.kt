package com.example.astoncourseproject.domain.usecase

import com.example.astoncourseproject.data.repository.EpisodeByIdRepository

import com.example.astoncourseproject.domain.models.Episode

class GetEpisodeByIdUseCase constructor(private val repository: EpisodeByIdRepository) {

    private val list = mutableListOf<Episode>()

    suspend fun execute(id: String): List<Episode> {
        val response = repository.getEpisodeById(id)

        if (response.isSuccessful) {
            val episode = Episode(
                id = response.body()!!.id,
                name = response.body()!!.name,
                air_date = response.body()!!.airDate,
                episode = response.body()!!.episode
            )
            list.add(episode)
        }
        return list
    }
}