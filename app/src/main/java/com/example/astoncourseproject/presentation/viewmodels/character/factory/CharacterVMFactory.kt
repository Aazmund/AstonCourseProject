package com.example.astoncourseproject.presentation.viewmodels.character.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.character.CharactersListService
import com.example.astoncourseproject.data.repository.character.CharacterDbRepository
import com.example.astoncourseproject.data.repository.character.CharactersListRepository
import com.example.astoncourseproject.domain.usecase.character.GetCharacterListUseCase
import com.example.astoncourseproject.presentation.viewmodels.character.CharacterViewModel

class CharacterVMFactory(private val application: Application): ViewModelProvider.Factory {

    private val charactersListService = CharactersListService.getInstance()
    private val repository = CharactersListRepository(charactersListService)
    private val getCharacterListUseCase = GetCharacterListUseCase(repository)
    private val characterDbRepository = CharacterDbRepository(application)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(application, getCharacterListUseCase, characterDbRepository) as T
    }
}