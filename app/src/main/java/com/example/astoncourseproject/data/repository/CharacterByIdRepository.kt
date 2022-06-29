package com.example.astoncourseproject.data.repository

import com.example.astoncourseproject.data.network.CharacterByIdService

class CharacterByIdRepository constructor(private val retrofitService: CharacterByIdService) {
    suspend fun getCharacterById(id:String) = retrofitService.getCharacterById(id)
}