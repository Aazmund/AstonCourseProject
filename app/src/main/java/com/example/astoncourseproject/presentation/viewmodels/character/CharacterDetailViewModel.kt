package com.example.astoncourseproject.presentation.viewmodels.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.models.Character
import com.example.astoncourseproject.domain.models.Episode
import com.example.astoncourseproject.domain.usecase.character.GetCharacterByIdUseCase
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeMultiIdUseCase

import kotlinx.coroutines.*

class CharacterDetailViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getEpisodeMultiIdUseCase: GetEpisodeMultiIdUseCase
) : ViewModel() {

    val liveData = MutableLiveData<List<Character>>()
    val episodeLiveData = MutableLiveData<List<Episode>>()
    private var job: Job? = null

    fun update(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getCharacterByIdUseCase.execute(id)
            }
        }
    }

    fun updateEpisodes(list: List<String>) {
        var ids = ""
        for (obj in list) {
            ids += obj.substring(obj.lastIndexOf("/") + 1, obj.length)
            ids += ","
        }
        ids = ids.removeRange(ids.length - 1, ids.length)
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                episodeLiveData.value = getEpisodeMultiIdUseCase.execute(listOf(ids))
            }
        }
    }
}