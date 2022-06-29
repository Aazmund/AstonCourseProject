package com.example.astoncourseproject.domain.usecase

import com.example.astoncourseproject.data.repository.EpisodeRepository
import com.example.astoncourseproject.domain.models.Episode

class GetEpisodeListUseCase constructor(private val repository: EpisodeRepository) {

    private val list = mutableListOf<Episode>()

    suspend fun execute(): List<Episode> {
        list.clear()

        val response = repository.getAllEpisode()

        if(response.isSuccessful){
            for (obj in response.body()!!.result){
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