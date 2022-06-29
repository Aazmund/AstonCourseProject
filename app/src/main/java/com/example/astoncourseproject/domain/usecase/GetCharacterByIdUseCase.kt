package com.example.astoncourseproject.domain.usecase

import com.example.astoncourseproject.data.repository.CharacterByIdRepository
import com.example.astoncourseproject.domain.models.Character

class GetCharacterByIdUseCase constructor(private val repository: CharacterByIdRepository) {

    private val list = mutableListOf<Character>()

    suspend fun execute(id: String): List<Character>{
        val response = repository.getCharacterById(id)

        if (response.isSuccessful){
            println(response.body()!!)
            val character = Character(
                id = response.body()!!.id,
                name = response.body()!!.name,
                image = response.body()!!.image,
                status = response.body()!!.status,
                species = response.body()!!.species,
                gender = response.body()!!.gender
            )
            list.add(character)
//            if(response.body()?.result != null){
//                for (obj in response.body()!!.result) {
//                    val character = Character(
//                        id = response.body()!!.id,
//                        name = response.body()!!.name,
//                        image = response.body()!!.image,
//                        status = response.body()!!.status,
//                        species = response.body()!!.species,
//                        gender = response.body()!!.gender
//                    )
//                    list.add(character)
//                }
//                println(list)
//            }
//            list.add(character)
        }
        println(list)
        return list
    }
}