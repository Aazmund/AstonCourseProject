package com.example.astoncourseproject.domain.usecase.episode

import com.example.astoncourseproject.data.repository.episode.EpisodeListRepository
import com.example.astoncourseproject.domain.models.Episode

class GetEpisodeListUseCase constructor(private val repository: EpisodeListRepository) {

    suspend fun execute(page: Int): List<Episode> {
        val list = mutableListOf<Episode>()
        val response = repository.getAllEpisode(page)

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