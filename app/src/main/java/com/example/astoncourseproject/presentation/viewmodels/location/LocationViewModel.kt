package com.example.astoncourseproject.presentation.viewmodels.location

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.astoncourseproject.data.repository.location.LocationDbRepository
import com.example.astoncourseproject.domain.models.Location
import com.example.astoncourseproject.domain.usecase.location.GetLocationFilterListUseCase
import com.example.astoncourseproject.domain.usecase.location.GetLocationListUseCase
import com.example.astoncourseproject.utils.NetworkConnection
import kotlinx.coroutines.*

class LocationViewModel(
    application: Application,
    private val getLocationListUseCase: GetLocationListUseCase,
    private val locationDbRepository: LocationDbRepository,
    private val getLocationFilterListUseCase: GetLocationFilterListUseCase
) : AndroidViewModel(application) {

    val liveData = MutableLiveData<List<Location>>()
    private val listOfLocation = mutableListOf<Location>()

    private var job: Job? = null
    private var page = 1
    private val context = application

    private fun checkConnection(): Boolean {
        return NetworkConnection.isNetworkConnected(context)
    }

    fun update() {
        if (checkConnection()) {
            updateFromNetwork()
        } else {
            Toast.makeText(context, "No internet connection! Data loaded from local database", Toast.LENGTH_SHORT).show()
            updateFromLocal()
        }
    }

    private fun updateFromNetwork() {
        page = 1
        listOfLocation.clear()
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfLocation += getLocationListUseCase.execute(page)
                liveData.value = listOfLocation
                locationDbRepository.addLocationsList(listOfLocation)
                if (listOfLocation.isEmpty()){
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
            this.cancel()
        }
    }

    fun addNewPage() {
        page++
        if (checkConnection()){
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    listOfLocation += getLocationListUseCase.execute(page)
                    liveData.value = listOfLocation
                    locationDbRepository.addLocationsList(listOfLocation)
                }
                this.cancel()
            }
        }else{
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    listOfLocation += locationDbRepository.getLocationByPage(page)
                    liveData.value = listOfLocation
                }
                this.cancel()
            }
        }
    }

    private fun updateFromLocal() {
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfLocation += locationDbRepository.getLocationByPage(page)
                liveData.value = listOfLocation
            }
            this.cancel()
        }
    }

    fun registerFilterChanged(filters: Map<String, String>){
        if (checkConnection()){
            filterNetwork(filters)
        }else{
            filterLocal(filters["name"].toString())
        }
    }

    private fun filterNetwork(filters: Map<String, String>) {
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val list = getLocationFilterListUseCase.execute(filters)
                if (list.isEmpty()){
                    Toast.makeText(context, "Nothing found", Toast.LENGTH_SHORT).show()
                }else {
                    liveData.value = list
                }
            }
            this.cancel()
        }
    }

    private fun filterLocal(name: String){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val list = locationDbRepository.getLocationByName(name)
                if (list.isEmpty()){
                    Toast.makeText(context, "Nothing found", Toast.LENGTH_SHORT).show()
                }else {
                    liveData.value = list
                }
            }
            this.cancel()
        }
    }
}