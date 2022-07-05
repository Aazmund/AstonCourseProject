package com.example.astoncourseproject.presentation.viewmodels.location.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.location.LocationFilterService
import com.example.astoncourseproject.data.network.location.LocationsListService
import com.example.astoncourseproject.data.repository.location.LocationDbRepository
import com.example.astoncourseproject.data.repository.location.LocationListRepository
import com.example.astoncourseproject.data.repository.location.LocationsFilterListRepository
import com.example.astoncourseproject.domain.usecase.location.GetLocationFilterListUseCase
import com.example.astoncourseproject.domain.usecase.location.GetLocationListUseCase
import com.example.astoncourseproject.presentation.viewmodels.location.LocationViewModel

class LocationVMFactory(private val application: Application) : ViewModelProvider.Factory {

    private val locationsListService = LocationsListService.getInstance()
    private val repository = LocationListRepository(locationsListService)
    private val getLocationListUseCase = GetLocationListUseCase(repository)

    private val locationFilterService = LocationFilterService.getInstance()
    private val filterRepo = LocationsFilterListRepository(locationFilterService)
    private val getLocationFilterListUseCase = GetLocationFilterListUseCase(filterRepo)

    private val locationDbRepository = LocationDbRepository(application)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(
            application,
            getLocationListUseCase,
            locationDbRepository,
            getLocationFilterListUseCase
        ) as T
    }
}