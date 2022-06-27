package com.example.astoncourseproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.usecase.GetCharacterListUseCase
import com.example.astoncourseproject.domain.models.Character
import kotlinx.coroutines.*

class CharacterViewModel(
    private val getCharacterListUseCase: GetCharacterListUseCase
): ViewModel() {

    val liveData = MutableLiveData<List<Character>>()
    private var job: Job? = null

    fun update(){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getCharacterListUseCase.execute()
            }
        }
    }
}