package com.example.movie.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val repository: com.example.core.data.repository.movie.MovieRepository
) : ViewModel() {

  /*  private var pageCounter: Int = FIRST_PAGE_NUMBER
    private var lastSuccessPage: Int = 0
    private var totalPages: Int = Int.MAX_VALUE

    private val _listItems = MutableStateFlow<List<com.example.core.model.MovieModel>>(listOf())
    val listState: StateFlow<List<com.example.core.model.MovieModel>> get() = _listItems

    private val _searchQueryState = MutableStateFlow("")
    val searchQueryState: StateFlow<String> = _searchQueryState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _noResultState = MutableStateFlow(false)
    val noAnyContentState: StateFlow<Boolean> get() = _noResultState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> get() = _errorState

    private var searchJob: Job? = null
    private var searchByQueryJob: Job? = null


    fun performSearch() {
        cancelSearchByQueryJob()
        reset()
        fetchData()
    }

    private fun reset() {
        cancelSearchJob()
        resetPageCounter()
        setListData(mutableListOf())
        setError(null)
        setNoResult(false)
        setLoading(false)
    }

    private fun fetchData() {
        setLoading(true)
        searchJob = viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.searchMovie(_searchQueryState.value, pageCounter)
            }
            response.getOrNull()?.let {
                handleData(it)
            } ?: run {
                handleError(response.leftOrNull())
            }
            setLoading(false)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQueryState.value = query
        if (!isQueryEmpty()) {
            initSearchByQueryJob()
        } else {
            cancelSearchByQueryJob()
            reset()
        }
    }

    private fun initSearchByQueryJob() {
        cancelSearchByQueryJob()
        searchByQueryJob = viewModelScope.launch {
            delay(PERFORM_SEARCH_BY_QUERY_DELAY_MILLISECOND)
            reset()
            fetchData()
        }
    }

    fun fetchNextPage() {
        if (!isLoading() && !isTotalPagesLoaded() && !isQueryEmpty()) {
            pageCounter = lastSuccessPage + 1
            fetchData()
        }
    }

    fun retry() {
        fetchData()
    }


    private fun handleData(newData: com.example.core.model.ListModel<com.example.core.model.MovieModel>) {
        totalPages = newData.totalPages
        lastSuccessPage = newData.page
        val combinedList = (_listItems.value + newData.results).distinctBy { it.id }
        setListData(combinedList)
        setNoResult(combinedList.isEmpty())
    }

    private fun handleError(error: CallError?) {
        setError(error.toHumanReadableText())
    }

    private fun cancelSearchByQueryJob() {
        searchByQueryJob?.cancel()
    }

    private fun cancelSearchJob() {
        searchJob?.cancel()
    }

    private fun resetPageCounter() {
        pageCounter = FIRST_PAGE_NUMBER
        lastSuccessPage = 0
    }

    private fun setError(errorText: String?) {
        _errorState.value = errorText
    }

    private fun setNoResult(isEnable: Boolean) {
        _noResultState.value = isEnable
    }

    private fun setLoading(isLoading: Boolean) {
        _loadingState.value = isLoading
    }

    private fun setListData(list: List<com.example.core.model.MovieModel>) {
        _listItems.value = list
    }

    private fun isLoading(): Boolean {
        return _loadingState.value
    }

    private fun isQueryEmpty(): Boolean {
        return _searchQueryState.value.isEmpty()
    }

    private fun isTotalPagesLoaded(): Boolean {
        return totalPages <= pageCounter
    }

    private companion object {
        const val PERFORM_SEARCH_BY_QUERY_DELAY_MILLISECOND = 1000L
    }*/


}




