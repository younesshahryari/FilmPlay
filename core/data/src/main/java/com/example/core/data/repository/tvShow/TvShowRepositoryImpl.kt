package com.example.core.data.repository.tvShow

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.model.TvShowModel
import com.example.core.data.repository.mapper.asExternalModel
import com.example.core.model.TvShowDetailModel
import com.example.core.network.datasource.tvshow.TvShowRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepositoryImpl @Inject constructor(private val tvShowRemoteDataSource: TvShowRemoteDataSource) :
    TvShowRepository {

    override suspend fun getTvShow(tvId: Int): Result<TvShowDetailModel> {
        return try {
            Result.success(tvShowRemoteDataSource.getTvShow(tvId).asExternalModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPopularTvShows(): Flow<PagingData<TvShowModel>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )
        return tvShowRemoteDataSource.getPopularTvShows(config)
            .map { pagingData -> pagingData.map { it.asExternalModel() } }
    }

    override suspend fun searchTvShows(
        query: String,
    ): Flow<PagingData<TvShowModel>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        )
        return tvShowRemoteDataSource.searchTvShows(query, config)
            .map { pagingData -> pagingData.map { it.asExternalModel() } }
    }

}