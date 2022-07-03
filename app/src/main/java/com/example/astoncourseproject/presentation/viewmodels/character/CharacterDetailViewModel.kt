package com.example.astoncourseproject.presentation.viewmodels.character

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.astoncourseproject.data.repository.character.CharacterDbRepository
import com.example.astoncourseproject.data.repository.episode.EpisodeDbRepository
import com.example.astoncourseproject.domain.models.Character
import com.example.astoncourseproject.domain.models.Episode
import com.example.astoncourseproject.domain.usecase.character.GetCharacterByIdUseCase
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeMultiIdUseCase
import com.example.astoncourseproject.utils.NetworkConnection

import kotlinx.coroutines.*

class CharacterDetailViewModel(
    application: Application,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getEpisodeMultiIdUseCase: GetEpisodeMultiIdUseCase,
    private val characterDbRepository: CharacterDbRepository,
    private val episodeDbRepository: EpisodeDbRepository
) : AndroidViewModel(application) {

    val liveData = MutableLiveData<List<Character>>()
    val episodeLiveData = MutableLiveData<List<Episode>>()

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

    fun updateEpisodes(list: List<String>) {
        var ids = ""
        for (obj in list) {
            ids += obj.substring(obj.lastIndexOf("/") + 1, obj.length)
            ids += ","
        }
        ids = ids.removeRange(ids.length - 1, ids.length)
        if (checkConnection()){
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    episodeLiveData.value = getEpisodeMultiIdUseCase.execute(listOf(ids))
                }
            }
        }else{
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    episodeLiveData.value = episodeDbRepository.getEpisodesByIds(listOf(ids))
                }
            }
        }
    }

    private fun updateFromNetwork(id: String){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getCharacterByIdUseCase.execute(id)
            }
        }
    }

    private fun updateFromLocal(id: String){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = listOf(characterDbRepository.getCharacterById(id))
            }
        }
    }
}