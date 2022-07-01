package com.example.astoncourseproject.data.repository.character

import com.example.astoncourseproject.data.network.character.CharactersListService

class CharactersListRepository constructor(private val retrofitService: CharactersListService) {
    suspend fun getAllCharacter(page: Int) = retrofitService.getCharacterList(page)
}