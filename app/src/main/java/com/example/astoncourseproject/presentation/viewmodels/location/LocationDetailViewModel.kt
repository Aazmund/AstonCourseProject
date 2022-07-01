package com.example.astoncourseproject.presentation.viewmodels.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.models.Character
import com.example.astoncourseproject.domain.models.Location
import com.example.astoncourseproject.domain.usecase.character.GetCharacterMultiIdUseCase
import com.example.astoncourseproject.domain.usecase.location.GetLocationByIdUseCase
import kotlinx.coroutines.*

class LocationDetailViewModel(
    private val getLocationByIdUseCase: GetLocationByIdUseCase,
    private val getCharacterMultiIdUseCase: GetCharacterMultiIdUseCase
) : ViewModel() {
    val liveData = MutableLiveData<List<Location>>()
    val characterLiveData = MutableLiveData<List<Character>>()
    private var job: Job? = null

    fun update(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getLocationByIdUseCase.execute(id)
            }
        }
    }

    fun updateCharacter(list: List<String>){
        var ids = ""
        for (obj in list) {
            ids += obj.substring(obj.lastIndexOf("/") + 1, obj.length)
            ids += ","
        }
        ids = ids.removeRange(ids.length - 1, ids.length)
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                characterLiveData.value = getCharacterMultiIdUseCase.execute(listOf(ids))
            }
        }
    }
}