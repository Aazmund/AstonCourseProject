package com.example.astoncourseproject.data.repository.character

import com.example.astoncourseproject.data.network.character.CharacterByIdService

class CharacterByIdRepository constructor(private val retrofitService: CharacterByIdService) {
    suspend fun getCharacterById(id: String) = retrofitService.getCharacterById(id)
}