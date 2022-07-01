package com.example.astoncourseproject.data.repository.character

import com.example.astoncourseproject.data.network.character.CharacterMultiIdService

class CharacterMultiIdRepository constructor(private val retrofitService: CharacterMultiIdService) {
    suspend fun getCharacterMultiId(id: List<String>) = retrofitService.getCharacterMultiId(id)
}