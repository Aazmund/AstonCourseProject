package com.example.astoncourseproject.domain.usecase.episode

import com.example.astoncourseproject.data.repository.episode.EpisodeByIdRepository
import com.example.astoncourseproject.domain.models.Episode

class GetEpisodeByIdUseCase constructor(private val repository: EpisodeByIdRepository) {

    suspend fun execute(id: String): List<Episode> {
        val list = mutableListOf<Episode>()
        val response = repository.getEpisodeById(id)

        if (response.isSuccessful && response.body() != null) {
            val episode = Episode(
                id = response.body()!!.id,
                name = response.body()!!.name,
                episode = response.body()!!.episode,
                air_date = response.body()!!.airDate,
                characters = response.body()!!.characters
            )
            list.add(episode)
        }
        return list
    }
}