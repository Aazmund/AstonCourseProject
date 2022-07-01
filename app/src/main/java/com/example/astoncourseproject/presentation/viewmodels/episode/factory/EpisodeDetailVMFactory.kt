package com.example.astoncourseproject.presentation.viewmodels.episode.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.character.CharacterMultiIdService
import com.example.astoncourseproject.data.network.episode.EpisodeByIdService
import com.example.astoncourseproject.data.repository.character.CharacterMultiIdRepository
import com.example.astoncourseproject.data.repository.episode.EpisodeByIdRepository
import com.example.astoncourseproject.domain.usecase.character.GetCharacterMultiIdUseCase
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeByIdUseCase
import com.example.astoncourseproject.presentation.viewmodels.episode.EpisodeDetailViewModel

class EpisodeDetailVMFactory: ViewModelProvider.Factory {

    private val episodeByIdService = EpisodeByIdService.getInstance()
    private val repository = EpisodeByIdRepository(episodeByIdService)
    private val getEpisodeByIdUseCase = GetEpisodeByIdUseCase(repository)

    private val characterMultiIdService = CharacterMultiIdService.getInstance()
    private val characterRepository = CharacterMultiIdRepository(characterMultiIdService)
    private val getCharacterMultiIdUseCase = GetCharacterMultiIdUseCase(characterRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeDetailViewModel(getEpisodeByIdUseCase, getCharacterMultiIdUseCase) as T
    }
}