package com.example.astoncourseproject.presentation.viewmodels.location

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.data.repository.character.CharacterDbRepository
import com.example.astoncourseproject.data.repository.location.LocationDbRepository
import com.example.astoncourseproject.domain.models.Character
import com.example.astoncourseproject.domain.models.Location
import com.example.astoncourseproject.domain.usecase.character.GetCharacterMultiIdUseCase
import com.example.astoncourseproject.domain.usecase.location.GetLocationByIdUseCase
import com.example.astoncourseproject.utils.NetworkConnection
import kotlinx.coroutines.*

class LocationDetailViewModel(
    application: Application,
    private val getLocationByIdUseCase: GetLocationByIdUseCase,
    private val getCharacterMultiIdUseCase: GetCharacterMultiIdUseCase,
    private val locationDbRepository: LocationDbRepository,
    private val characterDbRepository: CharacterDbRepository
) : ViewModel() {
    val liveData = MutableLiveData<List<Location>>()
    val characterLiveData = MutableLiveData<List<Character>>()

    private var job: Job? = null
    private val context = application

    private fun checkConnection(): Boolean {
        return NetworkConnection.isNetworkConnected(context)
    }

    fun update(id: String) {
        if (checkConnection()){
            updateFromNetwork(id)
        }else{
            Toast.makeText(context, "No internet connection! Data loaded from local database", Toast.LENGTH_SHORT).show()
            updateFromLocal(id)
        }
    }

    fun updateCharacter(list: List<String>){
        var ids = ""
        for (obj in list) {
            ids += obj.substring(obj.lastIndexOf("/") + 1, obj.length)
            ids += ","
        }
        ids = ids.removeRange(ids.length - 1, ids.length)
        if (checkConnection()){
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    characterLiveData.value = getCharacterMultiIdUseCase.execute(listOf(ids))
                }
            }
        }else{
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    characterLiveData.value = characterDbRepository.getCharacterByIds(listOf(ids))
                }
            }
        }
    }

    private fun updateFromNetwork(id: String){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getLocationByIdUseCase.execute(id)
            }
        }
    }

    private fun updateFromLocal(id: String){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = listOf(locationDbRepository.getLocationById(id))
            }
        }
    }
}