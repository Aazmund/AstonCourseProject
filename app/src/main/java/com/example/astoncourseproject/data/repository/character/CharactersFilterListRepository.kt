package com.example.astoncourseproject.data.repository.character

import com.example.astoncourseproject.data.network.character.CharacterFilterService

class CharactersFilterListRepository constructor(private val retrofitService: CharacterFilterService) {
    suspend fun getFilterCharacter(filters: Map<String, String>) = retrofitService.getCharacterWithFilters(filters)
}