package com.example.astoncourseproject.data.repository.episode

import android.content.Context
import com.example.astoncourseproject.data.dao.EpisodeDao
import com.example.astoncourseproject.data.database.AppDatabase
import com.example.astoncourseproject.data.entities.EpisodeEntity
import com.example.astoncourseproject.domain.models.Episode

class EpisodeDbRepository(context: Context) {
    private var db: EpisodeDao = AppDatabase.getInstance(context)?.episodeDao()!!

    fun getAllEpisodes() = db.getAllEpisodes()

    fun getEpisodeByPage(page: Int): List<Episode>{
        val list = mutableListOf<Episode>()
        for(obj in db.getEpisodeByPage((page - 1) * 20 + 1, (page - 1) * 20 + 20)) {
            val episode = Episode(
                obj.id,
                obj.name,
                obj.airDate,
                obj.episode,
                obj.characters.split(",").toList()
            )
        }
        return list
    }

    fun addEpisode(episode: Episode) {
        db.addEpisode(
            EpisodeEntity(
                episode.id,
                episode.name,
                episode.air_date,
                episode.episode,
                episode.characters.joinToString()
            )
        )
    }

    fun addListOfEpisodes(list: List<Episode>) {
        for (obj in list){
            addEpisode(obj)
        }
    }

    fun deleteEpisode(episode: Episode) {
        db.deleteEpisode(
            EpisodeEntity(
                episode.id,
                episode.name,
                episode.air_date,
                episode.episode,
                episode.characters.joinToString()
            )
        )
    }
}