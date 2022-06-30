package com.example.astoncourseproject.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.EpisodesListService
import com.example.astoncourseproject.data.repository.EpisodeListRepository
import com.example.astoncourseproject.domain.usecase.GetEpisodeListUseCase
import com.example.astoncourseproject.presentation.viewmodels.EpisodeViewModel

class EpisodeVMFactory : ViewModelProvider.Factory {

    private val episodesListService = EpisodesListService.getInstance()

    private val repository = EpisodeListRepository(episodesListService)

    private val getEpisodeListUseCase = GetEpisodeListUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeViewModel(getEpisodeListUseCase) as T
    }
}