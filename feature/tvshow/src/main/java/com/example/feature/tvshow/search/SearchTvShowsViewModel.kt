package com.example.feature.tvshow.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.data.repository.tvShow.TvShowRepository
import com.example.core.model.MovieModel
import com.example.core.model.TvShowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchTvShowsViewModel @Inject constructor(
    private val repository: TvShowRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val items: Flow<PagingData<TvShowModel>> = _searchQuery
        .flatMapLatest { query ->
            repository.searchTvShows(query)
        }
        .cachedIn(viewModelScope)

    fun searchMovie(query: String) {
        _searchQuery.value = query
    }

    val currentSearchQuery: Flow<String> = _searchQuery


}




