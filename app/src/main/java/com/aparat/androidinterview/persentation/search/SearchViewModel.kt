package com.aparat.androidinterview.persentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.retrofit.adapter.either.networkhandling.CallError
import arrow.retrofit.adapter.either.networkhandling.HttpError
import arrow.retrofit.adapter.either.networkhandling.IOError
import arrow.retrofit.adapter.either.networkhandling.UnexpectedCallError
import com.aparat.androidinterview.FIRST_PAGE_NUMBER
import com.aparat.androidinterview.data.repository.movie.MovieRepository
import com.aparat.androidinterview.persentation.model.ListModel
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

    private var page: Int = FIRST_PAGE_NUMBER
    private var totalPages: Int = Int.MAX_VALUE
    private val _mainListItems = MutableStateFlow<List<MovieModel>>(emptyList())
    val mainListItems: StateFlow<List<MovieModel>> get() = _mainListItems

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _noResult = MutableStateFlow(false)
    val noResult: StateFlow<Boolean> get() = _noResult

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private var searchJob: Job? = null

    fun fetchNextPage() {
        if (_loading.value.not() && _searchQuery.value.isNotEmpty() && totalPages > page) {
            page++
            fetchData()
        }
    }

    fun retry() {
        fetchData()
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
        page = FIRST_PAGE_NUMBER
        _mainListItems.value = emptyList()
        _loading.value = false
        _noResult.value = false
        _error.value = null
    }

    private fun fetchData() {
        _loading.value = true
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchMovie(_searchQuery.value, page)
            val data = response.getOrNull()
            withContext(Dispatchers.Main) {
                data?.let {
                    handleData(it)
                } ?: run {
                    handleError(response.leftOrNull())
                }
                _loading.value = false
            }
        }
    }

    private fun handleData(data: ListModel<MovieModel>) {
        totalPages = data.totalPages
        val newList = _mainListItems.value.toMutableSet()
        newList.addAll(data.results)
        _mainListItems.value = newList.toList()
        if (newList.isEmpty()) {
            _noResult.value = true
        }
    }

    private fun handleError(error: CallError?) {
        _error.value = when (error) {
            is HttpError -> "Http Error message! Tap to retry!"
            is IOError -> "Io Error message! Tap to retry!"
            is UnexpectedCallError -> "Unexpected call error message! Tap to retry!"
            else -> "Unknown Error! Tap to retry!"
        }
    }
}




