package com.example.astoncourseproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.models.Episode
import com.example.astoncourseproject.domain.usecase.GetEpisodeListUseCase
import kotlinx.coroutines.*

class EpisodeViewModel(
    private val getEpisodeListUseCase: GetEpisodeListUseCase
): ViewModel() {

    val liveData = MutableLiveData<List<Episode>>()
    private var job: Job? = null

    fun update(){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getEpisodeListUseCase.execute()
            }
        }
    }

}