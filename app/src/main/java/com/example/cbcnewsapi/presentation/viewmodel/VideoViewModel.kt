package com.example.cbcnewsapi.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.cbcnewsapi.data.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    repository: VideoRepository
) : ViewModel() {

    // CACHE
    val news_list_video_observable = repository.getNewsVideo().asLiveData()

    // Pagination
    private val currentState = MutableLiveData(DEFAULT)

    // Paging..
    val responsePage = currentState.switchMap { it ->
        repository.getVideoPageResult().cachedIn(viewModelScope)
    }

    companion object{
        private const val DEFAULT = ""
    }


}