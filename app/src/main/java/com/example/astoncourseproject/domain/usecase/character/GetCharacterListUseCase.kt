package com.example.astoncourseproject.domain.usecase.character

import com.example.astoncourseproject.data.repository.character.CharactersListRepository
import com.example.astoncourseproject.domain.models.Character

class GetCharacterListUseCase constructor(private val repository: CharactersListRepository) {

    suspend fun execute(page: Int): List<Character> {
        val list = mutableListOf<Character>()
        val response = repository.getAllCharacter(page)

        if (response.isSuccessful && response.body() != null) {
            for (obj in response.body()!!.result) {
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