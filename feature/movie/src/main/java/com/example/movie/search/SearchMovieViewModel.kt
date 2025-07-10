package com.example.movie.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.data.repository.movie.MovieRepository
import com.example.core.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val items: Flow<PagingData<MovieModel>> = _searchQuery
        .flatMapLatest { query ->
            repository.searchMovie(query)
        }
        .cachedIn(viewModelScope)

    fun searchMovie(query: String) {
        _searchQuery.value = query
    }

    val currentSearchQuery: Flow<String> = _searchQuery

}




