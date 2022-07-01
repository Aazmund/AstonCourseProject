package com.example.astoncourseproject.domain.usecase.character

import com.example.astoncourseproject.data.repository.character.CharacterMultiIdRepository
import com.example.astoncourseproject.domain.models.Character

class GetCharacterMultiIdUseCase constructor(private val repository: CharacterMultiIdRepository) {
    suspend fun execute(id: List<String>): List<Character> {
        val list = mutableListOf<Character>()
        val response = repository.getCharacterMultiId(id)
        println(response)
        println(response.body())

        if (response.isSuccessful && response.body() != null) {
            for (obj in response.body()!!){
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