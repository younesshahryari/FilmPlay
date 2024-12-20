package com.aparat.androidinterview.data.remoteData.dataSource.tvShow

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.data.remoteData.model.ResponseList
import com.aparat.androidinterview.data.remoteData.model.TvShowDetailResponse
import com.aparat.androidinterview.data.remoteData.model.TvShowResponse
import com.aparat.androidinterview.data.remoteData.service.TvShowApi

class TvShowRemoteDataSourceImpl(private val tvShowApi: TvShowApi) :
    TvShowRemoteDataSource {
    override suspend fun getTvShow(tvId: Int): Either<CallError, TvShowDetailResponse> {
        return tvShowApi.getTvShow(tvId)
    }

    override suspend fun getPopularTvShows(page: Int): Either<CallError, ResponseList<TvShowResponse>> {
        return tvShowApi.getPopularTvShows(page)
    }

    override suspend fun searchTvShows(
        query: String,
        page: Int
    ): Either<CallError, ResponseList<TvShowResponse>> {
        return tvShowApi.searchTvShows(query, page)
    }
}