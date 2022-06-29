package com.example.astoncourseproject.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.CharacterByIdService
import com.example.astoncourseproject.data.repository.CharacterByIdRepository
import com.example.astoncourseproject.domain.usecase.GetCharacterByIdUseCase
import com.example.astoncourseproject.presentation.viewmodels.CharacterDetailViewModel

class CharacterDetailVMFactory() : ViewModelProvider.Factory {

    private val characterByIdService = CharacterByIdService.getInstance()

    private val repository = CharacterByIdRepository(characterByIdService)

    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(getCharacterByIdUseCase) as T
    }


}