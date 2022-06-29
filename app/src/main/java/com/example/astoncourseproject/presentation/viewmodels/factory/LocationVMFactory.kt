package com.example.astoncourseproject.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.LocationsListService
import com.example.astoncourseproject.data.repository.LocationRepository
import com.example.astoncourseproject.domain.usecase.GetLocationListUseCase
import com.example.astoncourseproject.presentation.viewmodels.LocationViewModel

class LocationVMFactory : ViewModelProvider.Factory {

    private val locationsListService = LocationsListService.getInstance()

    private val repository = LocationRepository(locationsListService)

    private val getLocationListUseCase = GetLocationListUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(getLocationListUseCase) as T
    }
}