package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.RetrofitCharacterService

class CharacterRepository constructor(private val retrofitService: RetrofitCharacterService) {
    suspend fun getAllCharacter() = retrofitService.getCharacterList()
}