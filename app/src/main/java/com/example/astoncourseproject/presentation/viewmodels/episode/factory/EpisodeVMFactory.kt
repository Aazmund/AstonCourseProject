package com.example.astoncourseproject.presentation.viewmodels.episode.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.episode.EpisodeFilterService
import com.example.astoncourseproject.data.network.episode.EpisodesListService
import com.example.astoncourseproject.data.repository.episode.EpisodeDbRepository
import com.example.astoncourseproject.data.repository.episode.EpisodeListRepository
import com.example.astoncourseproject.data.repository.episode.EpisodesFilterListRepository
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeFilterListUseCase
import com.example.astoncourseproject.domain.usecase.episode.GetEpisodeListUseCase
import com.example.astoncourseproject.presentation.viewmodels.episode.EpisodeViewModel

class EpisodeVMFactory(private val application: Application) : ViewModelProvider.Factory {

    private val episodesListService = EpisodesListService.getInstance()
    private val repository = EpisodeListRepository(episodesListService)
    private val getEpisodeListUseCase = GetEpisodeListUseCase(repository)

    private val episodesFilterService = EpisodeFilterService.getInstance()
    private val filterRepo = EpisodesFilterListRepository(episodesFilterService)
    private val getEpisodeFilterListUseCase = GetEpisodeFilterListUseCase(filterRepo)

    private val episodeDbRepository = EpisodeDbRepository(application)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeViewModel(
            application,
            getEpisodeListUseCase,
            episodeDbRepository,
            getEpisodeFilterListUseCase
        ) as T
    }
}