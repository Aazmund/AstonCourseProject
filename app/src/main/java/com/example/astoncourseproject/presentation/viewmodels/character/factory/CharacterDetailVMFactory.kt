package com.example.astoncourseproject.presentation.viewmodels.character.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.character.CharacterByIdService
import com.example.astoncourseproject.data.network.episode.EpisodeMultiIdService
import com.example.astoncourseproject.data.repository.character.CharacterByIdRepository
import com.example.astoncourseproject.data.repository.character.CharacterDbRepository
import com.example.astoncourseproject.data.repository.episode.EpisodeDbRepository
import com.example.astoncourseproject.data.repository.episode.EpisodeMultiIdRepository
import com.example.astoncourseproject.domain.usecase.character.GetCharacterByIdUseCase
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeMultiIdUseCase
import com.example.astoncourseproject.presentation.viewmodels.character.CharacterDetailViewModel

class CharacterDetailVMFactory(private val application: Application) : ViewModelProvider.Factory {

    private val characterByIdService = CharacterByIdService.getInstance()
    private val repository = CharacterByIdRepository(characterByIdService)
    private val getCharacterByIdUseCase = GetCharacterByIdUseCase(repository)

    private val episodeMultiIdService = EpisodeMultiIdService.getInstance()
    private val episodeRepository = EpisodeMultiIdRepository(episodeMultiIdService)
    private val getEpisodeMultiIdUseCase = GetEpisodeMultiIdUseCase(episodeRepository)

    private val characterDbRepository = CharacterDbRepository(application)
    private val episodeDbRepository = EpisodeDbRepository(application)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(
            application,
            getCharacterByIdUseCase,
            getEpisodeMultiIdUseCase,
            characterDbRepository,
            episodeDbRepository
        ) as T
    }
}