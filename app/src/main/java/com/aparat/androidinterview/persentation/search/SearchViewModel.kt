package com.aparat.androidinterview.persentation.search

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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private var pageCounter: Int = FIRST_PAGE_NUMBER
    private var totalPages: Int = Int.MAX_VALUE

    private val _listItems = MutableStateFlow<MutableList<MovieModel>>(mutableListOf())
    val listItems: StateFlow<List<MovieModel>> get() = _listItems

    private val _searchQueryState = MutableStateFlow("")
    val searchQueryState: StateFlow<String> = _searchQueryState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _noResultState = MutableStateFlow(false)
    val noResultState: StateFlow<Boolean> get() = _noResultState

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
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchMovie(_searchQueryState.value, pageCounter)
            val data = response.getOrNull()
            withContext(Dispatchers.Main) {
                data?.let {
                    handleData(it)
                } ?: run {
                    handleError(response.leftOrNull())
                }
                setLoading(false)
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQueryState.value = query
        initSearchByQueryJob()
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
            pageCounter++
            fetchData()
        }
    }

    fun retry() {
        fetchData()
    }


    private fun handleData(data: ListModel<MovieModel>) {
        totalPages = data.totalPages
        val newList = _listItems.value
        newList.addAll(data.results)
        setListData(newList)
        setNoResult(newList.isEmpty())
    }

    private fun handleError(error: CallError?) {
        setError(
            when (error) {
                is HttpError -> "Http Error message! Tap to retry!"
                is IOError -> "Io Error message! Tap to retry!"
                is UnexpectedCallError -> "Unexpected call error message! Tap to retry!"
                else -> "Unknown Error! Tap to retry!"
            }
        )
    }

    private fun cancelSearchByQueryJob() {
        searchByQueryJob?.cancel()
    }

    private fun cancelSearchJob() {
        searchJob?.cancel()
    }

    private fun resetPageCounter() {
        pageCounter = FIRST_PAGE_NUMBER
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

    private fun setListData(list: MutableList<MovieModel>) {
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
    }


}




