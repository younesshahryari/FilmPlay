package com.aparat.androidinterview.persentation.search

import com.aparat.androidinterview.persentation.model.MovieModel

sealed interface SearchUiState {
    data object Loading : SearchUiState
    data object NotFound : SearchUiState
    data class Fail(val error: String) : SearchUiState
    data class Success(val list: List<MovieModel>) : SearchUiState
}