package com.example.astoncourseproject.presentation.viewmodels.location.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.location.LocationsListService
import com.example.astoncourseproject.data.repository.location.LocationListRepository
import com.example.astoncourseproject.domain.usecase.location.GetLocationListUseCase
import com.example.astoncourseproject.presentation.viewmodels.location.LocationViewModel

class LocationVMFactory : ViewModelProvider.Factory {

    private val locationsListService = LocationsListService.getInstance()

    private val repository = LocationListRepository(locationsListService)

    private val getLocationListUseCase = GetLocationListUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(getLocationListUseCase) as T
    }
}