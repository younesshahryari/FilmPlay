package com.aparat.androidinterview.data.remoteData.service

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.PAGE
import com.aparat.androidinterview.QUERY
import com.aparat.androidinterview.data.remoteData.model.ResponseList
import com.aparat.androidinterview.data.remoteData.model.TvShowDetailResponse
import com.aparat.androidinterview.data.remoteData.model.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApi {

    @GET("tv/{tv_id}")
    suspend fun getTvShow(
        @Path("tv_id") tvId: Int
    ): Either<CallError, TvShowDetailResponse>

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query(PAGE) page: Int
    ): Either<CallError, ResponseList<TvShowResponse>>

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query(QUERY) query: String,
        @Query(PAGE) page: Int
    ): Either<CallError, ResponseList<TvShowResponse>>
}
