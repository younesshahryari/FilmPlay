package com.example.movie.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.core.data.model.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val detailState: StateFlow<MovieDetailState> = _detailState.asStateFlow()

    private val id: Int = checkNotNull(savedStateHandle["movieId"])

    init {
        fetchMovieDetail()
    }

    fun fetchMovieDetail() {
        viewModelScope.launch {
            repository.getMovie(id).collect { result ->
                when (result) {
                    is Result.Loading -> _detailState.value = MovieDetailState.Loading
                    is Result.Success -> _detailState.value = MovieDetailState.Success(result.data)
                    is Result.Error -> _detailState.value = MovieDetailState.Error(result.message)
                }
            }
        }
    }
}




