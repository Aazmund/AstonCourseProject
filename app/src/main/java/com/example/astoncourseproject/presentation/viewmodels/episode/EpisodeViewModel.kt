package com.example.astoncourseproject.presentation.viewmodels.episode

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.data.repository.episode.EpisodeDbRepository
import com.example.astoncourseproject.domain.models.Episode
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeListUseCase
import com.example.astoncourseproject.utils.NetworkConnection
import kotlinx.coroutines.*

class EpisodeViewModel(
    application: Application,
    private val getEpisodeListUseCase: GetEpisodeListUseCase,
    private val episodeDbRepository: EpisodeDbRepository
) : AndroidViewModel(application) {

    val liveData = MutableLiveData<List<Episode>>()
    private val listOfEpisode = mutableListOf<Episode>()

    private var job: Job? = null
    private var page = 1
    private val context = application

    private fun checkConnection(): Boolean {
        return NetworkConnection.isNetworkConnected(context)
    }

    fun update() {
        if (checkConnection()){
            updateFromNetwork()
        }else{
            Toast.makeText(context, "No internet connection! Data loaded from local database", Toast.LENGTH_SHORT).show()
            updateFromLocal()
        }
    }

    private fun updateFromNetwork() {
        page = 1
        listOfEpisode.clear()
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfEpisode += getEpisodeListUseCase.execute(page)
                liveData.value = listOfEpisode
                episodeDbRepository.addListOfEpisodes(listOfEpisode)
                if (listOfEpisode.isEmpty()){
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun addNewPage() {
        page++
        if (checkConnection()){
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    listOfEpisode += getEpisodeListUseCase.execute(page)
                    liveData.value = listOfEpisode
                    episodeDbRepository.addListOfEpisodes(listOfEpisode)
                }
            }
        }else{
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    listOfEpisode += episodeDbRepository.getEpisodeByPage(page)
                    liveData.value = listOfEpisode
                }
            }
        }
    }

    private fun updateFromLocal() {
        page = 1
        listOfEpisode.clear()
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfEpisode += episodeDbRepository.getEpisodeByPage(page)
                liveData.value = listOfEpisode
            }
        }
    }
}