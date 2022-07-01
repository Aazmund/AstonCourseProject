package com.example.astoncourseproject.domain.usecase.character

import com.example.astoncourseproject.data.repository.character.CharacterByIdRepository
import com.example.astoncourseproject.domain.models.Character

class GetCharacterByIdUseCase constructor(private val repository: CharacterByIdRepository) {

    suspend fun execute(id: String): List<Character> {
        val list = mutableListOf<Character>()
        val response = repository.getCharacterById(id)

        if (response.isSuccessful && response.body() != null) {
            val character = Character(
                id = response.body()!!.id,
                name = response.body()!!.name,
                image = response.body()!!.image,
                status = response.body()!!.status,
                species = response.body()!!.species,
                gender = response.body()!!.gender,
                origin = response.body()!!.origin,
                location = response.body()!!.location,
                episode = response.body()!!.episode
            )
            list.add(character)
        }
        return list
    }
}