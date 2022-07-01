package com.example.astoncourseproject.presentation.viewmodels.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.models.Episode
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeListUseCase
import kotlinx.coroutines.*

class EpisodeViewModel(
    private val getEpisodeListUseCase: GetEpisodeListUseCase
) : ViewModel() {

    val liveData = MutableLiveData<List<Episode>>()
    private val listOfEpisode = mutableListOf<Episode>()
    private var job: Job? = null
    private var page = 1

    fun update() {
        page = 1
        listOfEpisode.clear()
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfEpisode += getEpisodeListUseCase.execute(page)
                liveData.value = listOfEpisode
            }
        }
    }

    fun addNewPage() {
        page++
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfEpisode += getEpisodeListUseCase.execute(page)
                liveData.value = listOfEpisode
            }
        }
    }
}