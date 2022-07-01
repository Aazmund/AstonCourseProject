package com.example.astoncourseproject.presentation.viewmodels.character.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.character.CharacterByIdService
import com.example.astoncourseproject.data.network.episode.EpisodeMultiIdService
import com.example.astoncourseproject.data.repository.character.CharacterByIdRepository
import com.example.astoncourseproject.data.repository.episode.EpisodeMultiIdRepository
import com.example.astoncourseproject.domain.usecase.character.GetCharacterByIdUseCase
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeMultiIdUseCase
import com.example.astoncourseproject.presentation.viewmodels.character.CharacterDetailViewModel

class CharacterDetailVMFactory : ViewModelProvider.Factory {

    private val characterByIdService = CharacterByIdService.getInstance()
    private val repository = CharacterByIdRepository(characterByIdService)
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    private val episodeMultiIdService = EpisodeMultiIdService.getInstance()
    private val episodeRepository = EpisodeMultiIdRepository(episodeMultiIdService)
    private val getEpisodeMultiIdUseCase = GetEpisodeMultiIdUseCase(episodeRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(getCharacterByIdUseCase, getEpisodeMultiIdUseCase) as T
    }
}