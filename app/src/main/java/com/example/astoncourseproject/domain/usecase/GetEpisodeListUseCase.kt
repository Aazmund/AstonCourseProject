package com.example.astoncourseproject.domain.usecase

import com.example.astoncourseproject.data.repository.EpisodeListRepository
import com.example.astoncourseproject.domain.models.Episode

class GetEpisodeListUseCase constructor(private val repository: EpisodeListRepository) {

    private val list = mutableListOf<Episode>()

    suspend fun execute(page: Int): List<Episode> {
        list.clear()

        val response = repository.getAllEpisode(page)

        if (response.isSuccessful && response.body() != null) {
            for (obj in response.body()!!.result) {
                val episode = Episode(
                    id = obj.id,
                    name = obj.name,
                    episode = obj.episode,
                    air_date = obj.airDate
                )
                list.add(episode)
            }
        }
        return list
    }
}