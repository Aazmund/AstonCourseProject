package com.example.astoncourseproject.domain.usecase

import com.example.astoncourseproject.data.repository.CharacterRepository
import com.example.astoncourseproject.domain.models.Character

class GetCharacterListUseCase constructor(private val repository: CharacterRepository) {

    private val list = mutableListOf<Character>()

    suspend fun execute(): List<Character> {
        list.clear()

        val response = repository.getAllCharacter()

        if (response.isSuccessful) {
            for (obj in response.body()!!.result) {
                val character = Character(
                    name = obj.name,
                    image = obj.image,
                    status = obj.status,
                    species = obj.species,
                    gender = obj.gender
                )
                list.add(character)
            }
        }
        return list
    }
}