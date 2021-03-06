package com.example.astoncourseproject.presentation.viewmodels.episode.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.character.CharacterMultiIdService
import com.example.astoncourseproject.data.network.episode.EpisodeByIdService
import com.example.astoncourseproject.data.repository.character.CharacterDbRepository
import com.example.astoncourseproject.data.repository.character.CharacterMultiIdRepository
import com.example.astoncourseproject.data.repository.episode.EpisodeByIdRepository
import com.example.astoncourseproject.data.repository.episode.EpisodeDbRepository
import com.example.astoncourseproject.domain.usecase.character.GetCharacterMultiIdUseCase
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeByIdUseCase
import com.example.astoncourseproject.presentation.viewmodels.episode.EpisodeDetailViewModel

class EpisodeDetailVMFactory(private val application: Application) : ViewModelProvider.Factory {

    private val episodeByIdService = EpisodeByIdService.getInstance()
    private val repository = EpisodeByIdRepository(episodeByIdService)
    private val getEpisodeByIdUseCase = GetEpisodeByIdUseCase(repository)

    private val characterMultiIdService = CharacterMultiIdService.getInstance()
    private val characterRepository = CharacterMultiIdRepository(characterMultiIdService)
    private val getCharacterMultiIdUseCase = GetCharacterMultiIdUseCase(characterRepository)

    private val episodeDbRepository = EpisodeDbRepository(application)
    private val characterDbRepository = CharacterDbRepository(application)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeDetailViewModel(
            application,
            getEpisodeByIdUseCase,
            getCharacterMultiIdUseCase,
            episodeDbRepository,
            characterDbRepository
        ) as T
    }
}