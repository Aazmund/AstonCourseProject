package com.example.astoncourseproject.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.EpisodeByIdService
import com.example.astoncourseproject.data.repository.EpisodeByIdRepository
import com.example.astoncourseproject.domain.usecase.GetEpisodeByIdUseCase
import com.example.astoncourseproject.presentation.viewmodels.EpisodeDetailViewModel

class EpisodeDetailVMFactory: ViewModelProvider.Factory {

    private val episodeByIdService = EpisodeByIdService.getInstance()
    private val repository = EpisodeByIdRepository(episodeByIdService)
    private val getEpisodeByIdUseCase = GetEpisodeByIdUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeDetailViewModel(getEpisodeByIdUseCase) as T
    }
}