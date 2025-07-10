package com.example.movie.detail

import com.example.core.model.MovieDetailModel

sealed class MovieDetailState {
    object Loading : MovieDetailState()
    data class Success(val movie: MovieDetailModel) : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
}