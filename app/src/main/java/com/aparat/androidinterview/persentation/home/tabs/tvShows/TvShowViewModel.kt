package com.aparat.androidinterview.persentation.home.tabs.tvShows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.FIRST_PAGE_NUMBER
import com.aparat.androidinterview.data.repository.tvShow.TvShowRepository
import com.aparat.androidinterview.persentation.extensions.toHumanReadableText
import com.aparat.androidinterview.persentation.model.ListModel
import com.aparat.androidinterview.persentation.model.TvShowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repository: TvShowRepository
) : ViewModel() {

    private var pageCounter: Int = FIRST_PAGE_NUMBER
    private var lastSuccessPage: Int = 0
    private var totalPages: Int = Int.MAX_VALUE

    private val _listItems = MutableStateFlow<List<TvShowModel>>(listOf())
    val listItems: StateFlow<List<TvShowModel>> get() = _listItems

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _noResultState = MutableStateFlow(false)
    val noResultState: StateFlow<Boolean> get() = _noResultState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> get() = _errorState

    init {
        fetchData()
    }

    private fun fetchData() {
        setLoading(true)
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.getPopularTvShows(pageCounter)
            }
            response.getOrNull()?.let {
                handleData(it)
            } ?: run {
                handleError(response.leftOrNull())
            }
            setLoading(false)
        }
    }


    fun fetchNextPage() {
        if (!isLoading() && !isTotalPagesLoaded()) {
            pageCounter = lastSuccessPage + 1
            fetchData()
        }
    }

    fun retry() {
        fetchData()
    }

    private fun handleData(data: ListModel<TvShowModel>) {
        totalPages = data.totalPages
        lastSuccessPage = data.page
        val combinedList = (_listItems.value + data.results).distinctBy { it.id }
        setListData(combinedList)
        setNoResult(combinedList.isEmpty())
    }

    private fun handleError(error: CallError?) {
        setError(error.toHumanReadableText())
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

    private fun setListData(list: List<TvShowModel>) {
        _listItems.value = list
    }

    private fun isLoading(): Boolean {
        return _loadingState.value
    }

    private fun isTotalPagesLoaded(): Boolean {
        return totalPages <= pageCounter
    }
}
