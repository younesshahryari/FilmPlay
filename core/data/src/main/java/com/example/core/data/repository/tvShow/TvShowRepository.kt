package com.example.core.data.repository.tvShow

import androidx.paging.PagingData
import com.example.core.data.model.Result
import com.example.core.model.TvShowDetailModel
import com.example.core.model.TvShowModel
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    suspend fun getTvShow(
        tvId: Int
    ): Flow<Result<TvShowDetailModel>>

    fun getPopularTvShows(
    ): Flow<PagingData<TvShowModel>>

    fun searchTvShows(
        query: String,
    ): Flow<PagingData<TvShowModel>>

}