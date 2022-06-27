package com.example.astoncourseproject.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.RetrofitCharacterService
import com.example.astoncourseproject.data.repository.CharacterRepository
import com.example.astoncourseproject.domain.usecase.GetCharacterListUseCase
import com.example.astoncourseproject.presentation.viewmodels.CharacterViewModel

class CharacterVMFactory: ViewModelProvider.Factory {

    private val retrofitCharacterService = RetrofitCharacterService.getInstance()

    private val repository = CharacterRepository(retrofitCharacterService)

    private val getCharacterListUseCase = GetCharacterListUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(getCharacterListUseCase) as T
    }
}