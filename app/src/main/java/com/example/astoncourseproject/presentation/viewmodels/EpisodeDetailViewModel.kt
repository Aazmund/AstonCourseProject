package com.example.astoncourseproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.models.Episode
import com.example.astoncourseproject.domain.usecase.GetEpisodeByIdUseCase
import kotlinx.coroutines.*

class EpisodeDetailViewModel(
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase
) : ViewModel() {
    val liveData = MutableLiveData<List<Episode>>()
    private var job: Job? = null

    fun update(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getEpisodeByIdUseCase.execute(id)
            }
        }
    }
}