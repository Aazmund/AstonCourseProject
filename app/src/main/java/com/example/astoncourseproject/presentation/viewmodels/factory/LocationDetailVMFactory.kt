package com.example.astoncourseproject.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.LocationByIdService
import com.example.astoncourseproject.data.repository.LocationByIdRepository
import com.example.astoncourseproject.domain.usecase.GetLocationByIdUseCase
import com.example.astoncourseproject.presentation.viewmodels.LocationDetailViewModel

class LocationDetailVMFactory: ViewModelProvider.Factory {

    private val locationByIdService = LocationByIdService.getInstance()
    private val repository = LocationByIdRepository(locationByIdService)
    private val getLocationByIdUseCase = GetLocationByIdUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationDetailViewModel(getLocationByIdUseCase) as T
    }
}