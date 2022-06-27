package com.example.astoncourseproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.models.Location
import com.example.astoncourseproject.domain.usecase.GetLocationListUseCase
import kotlinx.coroutines.*

class LocationViewModel(
    private val getLocationListUseCase: GetLocationListUseCase
):ViewModel() {

    val liveData = MutableLiveData<List<Location>>()
    private var job: Job? = null

    fun update(){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getLocationListUseCase.execute()
            }
        }
    }
}