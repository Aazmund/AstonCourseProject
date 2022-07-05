package com.example.astoncourseproject.domain.usecase.episode

import com.example.astoncourseproject.data.network.episode.EpisodeFilterService
import com.example.astoncourseproject.data.repository.episode.EpisodesFilterListRepository
import com.example.astoncourseproject.domain.models.Episode

class GetEpisodeFilterListUseCase constructor(private val repository: EpisodesFilterListRepository) {
    suspend fun execute(map: Map<String, String>): List<Episode> {
        val list = mutableListOf<Episode>()
        val response = repository.getFilterEpisode(map)

        if (response.isSuccessful && response.body() != null) {
            for (obj in response.body()!!.result) {
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