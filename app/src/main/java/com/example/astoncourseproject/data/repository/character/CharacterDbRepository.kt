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

    fun getCharacterById(id: String): Character{
        var character:Character
        try {
            character =  Character(
                db.getCharacterById(id).id,
                db.getCharacterById(id).name,
                db.getCharacterById(id).status,
                db.getCharacterById(id).species,
                db.getCharacterById(id).gender,
                mapOf(db.getCharacterById(id).origin.originName to db.getCharacterById(id).origin.originUrl),
                mapOf(db.getCharacterById(id).location.locationName to db.getCharacterById(id).location.locationUrl),
                db.getCharacterById(id).image,
                db.getCharacterById(id).episode.split(",").toList()
            )
        }catch (e: Exception){
            character =  Character(
                "no local data",
                "no local data",
                "no local data",
                "no local data",
                "no local data",
                mapOf("name" to "no local data", "url" to "no local data"),
                mapOf("name" to "no local data", "url" to "no local data"),
                "",
                listOf("no local data", "no local data")
            )
        }
        return character
    }

    fun getCharacterByIds(ids: List<String>): List<Character>{
        val list = mutableListOf<Character>()
        for (id in ids){
            list.add(getCharacterById(id))
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