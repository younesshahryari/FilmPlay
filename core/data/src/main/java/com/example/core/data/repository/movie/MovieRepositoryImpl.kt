package com.example.core.data.repository.movie

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.data.model.Result
import com.example.core.data.repository.mapper.asExternalModel
import com.example.core.model.MovieDetailModel
import com.example.core.model.MovieModel
import com.example.core.network.datasource.movie.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) :
    MovieRepository {

    override suspend fun getMovie(movieId: Int): Flow<Result<MovieDetailModel>> = flow {
        emit(Result.Loading)
        try {
            val response = movieRemoteDataSource.getMovie(movieId)
            emit(Result.Success(response.asExternalModel()))
        } catch (e: Exception) {
            emit(Result.Error(e.localizedMessage ?: "An unknown error occurred"))
        }
    }

    override fun getPopularMovies(): Flow<PagingData<MovieModel>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )
        return movieRemoteDataSource.getPopularMovies(config)
            .map { pagingData -> pagingData.map { it.asExternalModel().also { it.thumbnail } } }
    }

    override fun searchMovie(
        query: String,
    ): Flow<PagingData<MovieModel>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )
        return movieRemoteDataSource.searchMovie(config, query)
            .map { pagingData -> pagingData.map { it.asExternalModel() } }
    }


}