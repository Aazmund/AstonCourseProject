package com.example.astoncourseproject.domain.usecase.episode

import com.example.astoncourseproject.data.repository.episode.EpisodeMultiIdRepository
import com.example.astoncourseproject.domain.models.Episode

class GetEpisodeMultiIdUseCase constructor(private val repository: EpisodeMultiIdRepository) {
    suspend fun execute(id: List<String>): List<Episode> {
        val list = mutableListOf<Episode>()
        val response = repository.getEpisodeMultiId(id)

        if (response.isSuccessful && response.body() != null) {
            for (obj in response.body()!!) {
                val episode = Episode(
                    id = obj.id,
                    name = obj.name,
                    episode = obj.episode,
                    air_date = obj.airDate,
                    characters = obj.characters
                )
                list.add(episode)
            }
        }
        return list
    }
}