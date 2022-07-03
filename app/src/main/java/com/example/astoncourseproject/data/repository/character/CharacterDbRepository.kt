package com.example.astoncourseproject.data.repository.character

import android.content.Context
import com.example.astoncourseproject.data.dao.CharacterDao
import com.example.astoncourseproject.data.database.AppDatabase
import com.example.astoncourseproject.data.entities.character.CharacterEntity
import com.example.astoncourseproject.data.entities.character.Location
import com.example.astoncourseproject.data.entities.character.Origin
import com.example.astoncourseproject.domain.models.Character

class CharacterDbRepository(context: Context) {
    private var db: CharacterDao = AppDatabase.getInstance(context)?.characterDao()!!

    fun getAllCharacters() = db.getAllCharacters()

    fun getCharacterByPage(page: Int): List<Character>{
        val list = mutableListOf<Character>()
        for (obj in db.getCharactersByPage((page - 1) * 20 + 1, (page - 1) * 20 + 20)){
            val character = Character(
                obj.id,
                obj.name,
                obj.status,
                obj.species,
                obj.gender,
                mapOf(obj.origin.originName to obj.origin.originUrl),
                mapOf(obj.location.locationName to obj.location.locationUrl),
                obj.image,
                obj.episode.split(",").toList()
            )
            list.add(character)
        }
        return list
    }

    private fun addCharacter(character: Character) {
        db.addCharacter(
            CharacterEntity(
                character.id,
                character.name,
                character.status,
                character.species,
                character.gender,
                Origin(character.origin["name"].toString(), character.origin["url"].toString()),
                Location(
                    character.location["name"].toString(),
                    character.location["url"].toString()
                ),
                character.image,
                character.episode.joinToString()
            )
        )
    }

    fun addListOfCharacters(list: List<Character>) {
        for (obj in list) {
            addCharacter(obj)
        }
    }

    fun deleteCharacter(character: Character) {
        db.deleteCharacter(
            CharacterEntity(
                character.id,
                character.name,
                character.status,
                character.species,
                character.gender,
                Origin(character.origin["name"].toString(), character.origin["url"].toString()),
                Location(
                    character.location["name"].toString(),
                    character.location["url"].toString()
                ),
                character.image,
                character.episode.joinToString()
            )
        )
    }
}