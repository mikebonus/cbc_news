package com.example.cbcnewsapi.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.cbcnewsapi.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    repository: NewsRepository,
    @Assisted state : SavedStateHandle
) : ViewModel() {

    // Cache
    val news_list_observable = repository.getNews().asLiveData()

    // Pagination
    private val currentState = state.getLiveData(DEFAULT, CURRENT)      // when 'searching' feature is involved, this handles PROCESS-DEATH..

    // Paging..
    val responsePage = currentState.switchMap { it ->
        repository.getNewsPageResult().cachedIn(viewModelScope)
    }

    companion object{
        private const val CURRENT = ""
        private const val DEFAULT = ""
    }


}