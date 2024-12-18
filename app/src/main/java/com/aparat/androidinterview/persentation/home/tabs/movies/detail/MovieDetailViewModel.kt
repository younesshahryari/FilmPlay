package com.aparat.androidinterview.persentation.home.tabs.movies.detail

import android.util.Log
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
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _data = MutableStateFlow<MovieModel?>(null)
    val data: StateFlow<MovieModel?> get() = _data

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> get() = _error

    fun fetchData(id: Int) {
        _loading.value = true
        viewModelScope.launch {
            val response = repository.getMovie(id)
            val result = response.getOrNull()
            result?.let {
                _data.value = it
            }
            val error = response.leftOrNull()
            error?.let {
                when (it) {
                    is arrow.retrofit.adapter.either.networkhandling.HttpError -> Log.i("response",it.message)
                    is arrow.retrofit.adapter.either.networkhandling.IOError -> Log.i("response",it.cause.message+"")
                    is arrow.retrofit.adapter.either.networkhandling.UnexpectedCallError -> Log.i("response",it.cause.message+"")
                }
                _error.value = true
            }
            _loading.value = false
        }
    }

    fun retry(id: Int) {
        _error.value = false
        fetchData(id)
    }
}




