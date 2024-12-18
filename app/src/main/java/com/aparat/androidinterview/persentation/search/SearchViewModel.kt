package com.aparat.androidinterview.persentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aparat.androidinterview.data.repository.movie.MovieRepository
import com.aparat.androidinterview.persentation.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }

    private var page: Int = INITIAL_PAGE_NUMBER
    private var totalPages: Int = Int.MAX_VALUE
    private val _mainListItems = MutableStateFlow<List<MovieModel>>(emptyList())
    val mainListItems: StateFlow<List<MovieModel>> get() = _mainListItems

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _noResult = MutableStateFlow(false)
    val noResult: StateFlow<Boolean> get() = _noResult

    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> get() = _error

    private var searchJob: Job? = null

    fun fetchNextPage() {
        if (_loading.value.not() && _searchQuery.value.isNotEmpty() && totalPages > page) {
            page++
            fetchData()
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun performSearch() {
        reset()
        fetchData()
    }

    private fun reset() {
        searchJob?.cancel()
        page = INITIAL_PAGE_NUMBER
        _mainListItems.value = emptyList()
        _loading.value = false
        _noResult.value = false
        _error.value = false
    }

    private fun fetchData() {
        _loading.value = true
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchMovie(_searchQuery.value, page)
            val result = response.getOrNull()
            withContext(Dispatchers.Main) {
                result?.let {
                    totalPages = it.totalPages
                    val newList = _mainListItems.value.toMutableSet()
                    newList.addAll(it.results)
                    _mainListItems.value = newList.toList()
                    if (_mainListItems.value.isEmpty()) {
                        _noResult.value = true
                    }
                }
                val error = response.leftOrNull()
                error?.let {
                    if (page != INITIAL_PAGE_NUMBER) {
                        _error.value = true
                    }
                }
                _loading.value = false
            }
        }
    }
}




