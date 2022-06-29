package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.CharactersListService

class CharactersListRepository constructor(private val retrofitService: CharactersListService) {
    suspend fun getAllCharacter() = retrofitService.getCharacterList()
}