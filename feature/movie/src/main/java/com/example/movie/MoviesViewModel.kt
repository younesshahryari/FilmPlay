package com.example.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.data.repository.movie.MovieRepository
import com.example.core.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {

    val movies: Flow<PagingData<MovieModel>> =
        repository.getPopularMovies().cachedIn(viewModelScope)

}




