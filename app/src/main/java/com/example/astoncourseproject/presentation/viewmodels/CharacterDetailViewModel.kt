package com.example.astoncourseproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.models.Character
import com.example.astoncourseproject.domain.usecase.GetCharacterByIdUseCase

import kotlinx.coroutines.*

class CharacterDetailViewModel (
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
):ViewModel(){

    val liveData = MutableLiveData<List<Character>>()
    private var job: Job? = null

    fun update(id: String){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getCharacterByIdUseCase.execute(id)
            }
        }
    }

}