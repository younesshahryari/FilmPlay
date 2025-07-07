package com.example.core.network.datasource.movie

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.network.model.MovieDetailResponse
import com.example.core.network.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {

    suspend fun getMovie(
        movieId: Int
    ): MovieDetailResponse

     fun getPopularMovies(pageConfig: PagingConfig): Flow<PagingData<MovieResponse>>

     fun searchMovie(
        pageConfig: PagingConfig,
        query: String,
    ): Flow<PagingData<MovieResponse>>
}