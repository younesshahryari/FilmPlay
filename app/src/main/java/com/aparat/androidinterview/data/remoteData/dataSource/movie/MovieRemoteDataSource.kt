package com.aparat.androidinterview.data.remoteData.dataSource.movie

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.data.remoteData.model.MovieResponse
import com.aparat.androidinterview.data.remoteData.model.ResponseList

interface MovieRemoteDataSource {

    suspend fun getMovie(
        movieId: Int
    ): Either<CallError, MovieResponse>

    suspend fun getPopularMovies(
        page: Int
    ): Either<CallError, ResponseList<MovieResponse>>

    suspend fun searchMovie(
        query: String,
        page: Int
    ): Either<CallError, ResponseList<MovieResponse>>
}