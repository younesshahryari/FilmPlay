package com.aparat.androidinterview.persentation.home.movies.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.data.repository.movie.MovieRepository
import com.aparat.androidinterview.persentation.extensions.toHumanReadableText
import com.aparat.androidinterview.persentation.model.MovieDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _dataState = MutableStateFlow<MovieDetailModel?>(null)
    val dataState: StateFlow<MovieDetailModel?> get() = _dataState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> get() = _errorState

    fun fetchData(id: Int) {
        setLoading(true)
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.getMovie(id)
            }
            response.getOrNull()?.let {
                setListData(it)
            } ?: run {
                handleError(response.leftOrNull())
            }
            setLoading(false)
        }
    }

    private fun handleError(error: CallError?) {
        setError(error.toHumanReadableText())
    }

    fun retry(id: Int) {
        setError(null)
        fetchData(id)
    }

    private fun setListData(data: MovieDetailModel) {
        _dataState.value = data
    }

    private fun setLoading(isLoading: Boolean) {
        _loadingState.value = isLoading
    }

    private fun setError(errorText: String?) {
        _errorState.value = errorText
    }


}




