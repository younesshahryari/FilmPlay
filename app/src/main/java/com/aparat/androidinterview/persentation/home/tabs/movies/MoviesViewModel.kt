package com.aparat.androidinterview.persentation.home.tabs.movies

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aparat.androidinterview.data.repository.movie.MovieRepository
import com.aparat.androidinterview.persentation.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private var page: Int = 1
    private val _mainListItems = MutableStateFlow<List<MovieModel>>(emptyList())
    val mainListItems: StateFlow<List<MovieModel>> get() = _mainListItems

    val scrollState = mutableStateOf(LazyGridState(0, 0))


    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    init {
        fetchData()
    }

    private fun fetchData() {
        _loading.value = true
        viewModelScope.launch {
            val response = repository.getPopularMovies(page)
            val result = response.getOrNull()
            result?.let {
                val newList = _mainListItems.value.toMutableSet()
                newList.addAll(it.results)
                _mainListItems.value = newList.toList()
            }
            _loading.value = false
        }
    }

    fun fetchNextPage() {
        if (_loading.value.not()) {
            page++
            fetchData()
        }
    }
}




