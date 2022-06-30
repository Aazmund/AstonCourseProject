package com.example.astoncourseproject.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astoncourseproject.domain.models.Location
import com.example.astoncourseproject.domain.usecase.GetLocationByIdUseCase
import kotlinx.coroutines.*

class LocationDetailViewModel(
    private val getLocationByIdUseCase: GetLocationByIdUseCase
) : ViewModel() {
    val liveData = MutableLiveData<List<Location>>()
    private var job: Job? = null

    fun update(id: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                liveData.value = getLocationByIdUseCase.execute(id)
            }
        }
    }
}