package com.aparat.androidinterview.data.repository.tvShow

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.persentation.model.ListModel
import com.aparat.androidinterview.persentation.model.TvShowModel

interface TvShowRepository {
    suspend fun getTvShow(
        tvId: Int
    ): Either<CallError, TvShowModel>

    suspend fun getPopularTvShows(
        page: Int
    ): Either<CallError, ListModel<TvShowModel>>

    suspend fun searchTvShows(
        query: String,
        page: Int
    ): Either<CallError, ListModel<TvShowModel>>

}