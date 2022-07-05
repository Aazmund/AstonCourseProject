package com.example.astoncourseproject.domain.usecase.character

import com.example.astoncourseproject.data.repository.character.CharactersFilterListRepository
import com.example.astoncourseproject.domain.models.Character

class GetCharacterFilterListUseCase constructor(private val repository: CharactersFilterListRepository) {
    suspend fun execute(map: Map<String, String>): List<Character> {
        val list = mutableListOf<Character>()
        val response = repository.getFilterCharacter(map)

        if (response.isSuccessful && response.body() != null) {
            for (obj in response.body()!!.result){
                val character = Character(
                    id = obj.id,
                    name = obj.name,
                    image = obj.image,
                    status = obj.status,
                    species = obj.species,
                    gender = obj.gender,
                    origin = obj.origin,
                    location = obj.location,
                    episode = obj.episode
                )
                list.add(character)
            }
        }
        return list
    }
}