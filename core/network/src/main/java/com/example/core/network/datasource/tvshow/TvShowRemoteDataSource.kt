package com.example.core.network.datasource.tvshow

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.network.model.TvShowDetailResponse
import com.example.core.network.model.TvShowResponse
import kotlinx.coroutines.flow.Flow

interface TvShowRemoteDataSource {
    suspend fun getTvShow(
        tvId: Int
    ): TvShowDetailResponse

     fun getPopularTvShows(
        pageConfig: PagingConfig
    ): Flow<PagingData<TvShowResponse>>

     fun searchTvShows(
        query: String,
        pageConfig: PagingConfig
    ):  Flow<PagingData<TvShowResponse>>
}