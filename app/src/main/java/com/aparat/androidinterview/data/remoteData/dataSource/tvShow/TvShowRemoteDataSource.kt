package com.aparat.androidinterview.data.remoteData.dataSource.tvShow

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.data.remoteData.model.ResponseList
import com.aparat.androidinterview.data.remoteData.model.TvShowResponse

interface TvShowRemoteDataSource {
    suspend fun getTvShow(
        tvId: Int
    ): Either<CallError, TvShowResponse>

    suspend fun getPopularTvShows(
        page: Int
    ): Either<CallError, ResponseList<TvShowResponse>>

    suspend fun searchTvShows(
        query: String,
        page: Int
    ): Either<CallError, ResponseList<TvShowResponse>>
}