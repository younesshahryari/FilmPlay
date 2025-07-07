package com.example.core.data.repository.movie

import androidx.paging.PagingData
import com.example.core.model.MovieDetailModel
import com.example.core.model.MovieModel
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    suspend fun getMovie(
        movieId: Int
    ): Result<MovieDetailModel>

    fun getPopularMovies(): Flow<PagingData<MovieModel>>

    fun searchMovie(
        query: String,
    ): Flow<PagingData<MovieModel>>

}