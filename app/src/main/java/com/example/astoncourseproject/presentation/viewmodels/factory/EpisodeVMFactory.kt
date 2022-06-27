package com.example.astoncourseproject.presentation.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.astoncourseproject.data.network.RetrofitEpisodeService
import com.example.astoncourseproject.data.repository.EpisodeRepository
import com.example.astoncourseproject.domain.usecase.GetEpisodeListUseCase
import com.example.astoncourseproject.presentation.viewmodels.EpisodeViewModel

class EpisodeVMFactory : ViewModelProvider.Factory {

    private val retrofitEpisodeService = RetrofitEpisodeService.getInstance()

    private val repository = EpisodeRepository(retrofitEpisodeService)

    private val getEpisodeListUseCase = GetEpisodeListUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeViewModel(getEpisodeListUseCase) as T
    }
}