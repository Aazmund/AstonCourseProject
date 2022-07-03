package com.example.astoncourseproject.presentation.viewmodels.location.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.character.CharacterMultiIdService
import com.example.astoncourseproject.data.network.location.LocationByIdService
import com.example.astoncourseproject.data.repository.character.CharacterDbRepository
import com.example.astoncourseproject.data.repository.character.CharacterMultiIdRepository
import com.example.astoncourseproject.data.repository.location.LocationByIdRepository
import com.example.astoncourseproject.data.repository.location.LocationDbRepository
import com.example.astoncourseproject.domain.usecase.character.GetCharacterMultiIdUseCase
import com.example.astoncourseproject.domain.usecase.location.GetLocationByIdUseCase
import com.example.astoncourseproject.presentation.viewmodels.location.LocationDetailViewModel

class LocationDetailVMFactory(private val application: Application) : ViewModelProvider.Factory {

    private val locationByIdService = LocationByIdService.getInstance()
    private val repository = LocationByIdRepository(locationByIdService)
    private val getLocationByIdUseCase = GetLocationByIdUseCase(repository)

    private val characterMultiIdService = CharacterMultiIdService.getInstance()
    private val characterRepository = CharacterMultiIdRepository(characterMultiIdService)
    private val getCharacterMultiIdUseCase = GetCharacterMultiIdUseCase(characterRepository)

    private val locationDbRepository = LocationDbRepository(application)
    private val characterDbRepository = CharacterDbRepository(application)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationDetailViewModel(
            application,
            getLocationByIdUseCase,
            getCharacterMultiIdUseCase,
            locationDbRepository,
            characterDbRepository
        ) as T
    }
}