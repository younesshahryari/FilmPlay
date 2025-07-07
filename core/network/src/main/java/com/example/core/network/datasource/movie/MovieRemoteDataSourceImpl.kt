package com.example.core.network.datasource.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.network.api.movie.MovieApi
import com.example.core.network.datasource.movie.pagingsource.PopularMoviePagingSource
import com.example.core.network.datasource.movie.pagingsource.SearchMoviePagingSource
import com.example.core.network.model.MovieDetailResponse
import com.example.core.network.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val movieApi: MovieApi) : MovieRemoteDataSource {

    override suspend fun getMovie(movieId: Int): MovieDetailResponse {
        return movieApi.getMovie(movieId)
    }

    override fun getPopularMovies(pageConfig: PagingConfig): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { PopularMoviePagingSource(apiService = movieApi) }
        ).flow
    }

    override fun searchMovie(
        pageConfig: PagingConfig,
        query: String,
    ): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = {
                SearchMoviePagingSource(
                    searchQuery = query,
                    apiService = movieApi
                )
            }
        ).flow
    }
}