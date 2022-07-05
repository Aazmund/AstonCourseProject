package com.example.astoncourseproject.data.repository.episode

import android.content.Context
import com.example.astoncourseproject.data.dao.EpisodeDao
import com.example.astoncourseproject.data.database.AppDatabase
import com.example.astoncourseproject.data.entities.EpisodeEntity
import com.example.astoncourseproject.domain.models.Episode

class EpisodeDbRepository(context: Context) {
    private var db: EpisodeDao = AppDatabase.getInstance(context)?.episodeDao()!!

    fun getAllEpisodes() = db.getAllEpisodes()

    fun getEpisodeByPage(page: Int): List<Episode> {
        val list = mutableListOf<Episode>()
        for (obj in db.getEpisodeByPage((page - 1) * 20 + 1, (page - 1) * 20 + 20)) {
            val episode = Episode(
                obj.id,
                obj.name,
                obj.airDate,
                obj.episode,
                obj.characters.split(",").toList()
            )
            list.add(episode)
        }
        return list
    }

    fun getEpisodeById(id: String): Episode {
        var episode: Episode
        try {
            episode = Episode(
                db.getEpisodeById(id).id,
                db.getEpisodeById(id).name,
                db.getEpisodeById(id).episode,
                db.getEpisodeById(id).airDate,
                db.getEpisodeById(id).characters.split(",").toList()
            )
        } catch (e: Exception) {
            episode =  Episode(
                "no local data",
                "no local data",
                "no local data",
                "no local data",
                listOf("no local data", "no local data")
            )
        }
        return episode
    }

    fun getEpisodeByName(name: String): List<Episode> {
        val list = mutableListOf<Episode>()
        try {
            for (obj in db.getEpisodeByName(name)) {
                val episode = Episode(
                    obj.id,
                    obj.name,
                    obj.airDate,
                    obj.episode,
                    obj.characters.split(",").toList()
                )
                list.add(episode)
            }
        }catch (e: Exception){}
        return list
    }

    fun getEpisodesByIds(ids: List<String>): List<Episode> {
        val list = mutableListOf<Episode>()
        for (id in ids) {
            list.add(getEpisodeById(id))
        }
        return list
    }

    private fun addEpisode(episode: Episode) {
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
        for (obj in list) {
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