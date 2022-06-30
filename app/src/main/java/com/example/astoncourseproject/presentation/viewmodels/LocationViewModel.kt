package com.example.astoncourseproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.models.Location
import com.example.astoncourseproject.domain.usecase.GetLocationListUseCase
import kotlinx.coroutines.*

class LocationViewModel(
    private val getLocationListUseCase: GetLocationListUseCase
) : ViewModel() {

    val liveData = MutableLiveData<List<Location>>()
    private val listOfLocation = mutableListOf<Location>()
    private var job: Job? = null
    private var page = 1

    fun update() {
        page = 1
        listOfLocation.clear()
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfLocation += getLocationListUseCase.execute(page)
                liveData.value = listOfLocation
            }
        }
    }

    fun addNewPage() {
        page++
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfLocation += getLocationListUseCase.execute(page)
                liveData.value = listOfLocation
            }
        }
    }
}