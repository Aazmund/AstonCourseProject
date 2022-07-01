package com.example.astoncourseproject.presentation.viewmodels.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.usecase.character.GetCharacterListUseCase
import com.example.astoncourseproject.domain.models.Character
import kotlinx.coroutines.*

class CharacterViewModel(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : ViewModel() {

    val liveData = MutableLiveData<List<Character>>()
    private val listOfCharacter = mutableListOf<Character>()
    private var job: Job? = null
    private var page = 1

    fun update() {
        page = 1
        listOfCharacter.clear()
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfCharacter += getCharacterListUseCase.execute(page)
                liveData.value = listOfCharacter
            }
        }
    }

    fun addNewPage() {
        page++
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfCharacter += getCharacterListUseCase.execute(page)
                liveData.value = listOfCharacter
            }
        }
    }
}