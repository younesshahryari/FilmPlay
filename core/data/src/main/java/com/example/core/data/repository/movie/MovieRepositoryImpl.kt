package com.example.core.data.repository.movie

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.data.repository.mapper.asExternalModel
import com.example.core.model.MovieDetailModel
import com.example.core.model.MovieModel
import com.example.core.network.datasource.movie.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) :
    MovieRepository {

    override suspend fun getMovie(movieId: Int): Result<MovieDetailModel> {
        return try {
            Result.success(movieRemoteDataSource.getMovie(movieId).asExternalModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getPopularMovies(): Flow<PagingData<MovieModel>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )
        return movieRemoteDataSource.getPopularMovies(config)
            .map { pagingData -> pagingData.map { it.asExternalModel() } }
    }

    override fun searchMovie(
        query: String,
    ): Flow<PagingData<MovieModel>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )
        return movieRemoteDataSource.getPopularMovies(config)
            .map { pagingData -> pagingData.map { it.asExternalModel() } }
    }


}