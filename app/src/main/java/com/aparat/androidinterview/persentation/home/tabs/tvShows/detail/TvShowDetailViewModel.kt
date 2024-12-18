package com.aparat.androidinterview.persentation.home.tabs.tvShows.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aparat.androidinterview.data.repository.tvShow.TvShowRepository
import com.aparat.androidinterview.persentation.model.TvShowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val repository: TvShowRepository
) : ViewModel() {

    private val _data = MutableStateFlow<TvShowModel?>(null)
    val data: StateFlow<TvShowModel?> get() = _data

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> get() = _error

    fun fetchData(id: Int) {
        _loading.value = true
        viewModelScope.launch {
            val response = repository.getTvShow(id)
            val result = response.getOrNull()
            result?.let {
                _data.value = it
            }
            val error = response.leftOrNull()
            error?.let {
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




