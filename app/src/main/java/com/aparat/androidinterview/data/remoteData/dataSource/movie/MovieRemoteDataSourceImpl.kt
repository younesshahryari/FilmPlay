package com.aparat.androidinterview.data.remoteData.dataSource.movie

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.data.remoteData.model.MovieDetailResponse
import com.aparat.androidinterview.data.remoteData.model.MovieResponse
import com.aparat.androidinterview.data.remoteData.model.ResponseList
import com.aparat.androidinterview.data.remoteData.service.MovieApi

class MovieRemoteDataSourceImpl(private val movieApi: MovieApi) :
    MovieRemoteDataSource {
    override suspend fun getMovie(movieId: Int): Either<CallError, MovieDetailResponse> {
        return movieApi.getMovie(movieId)
    }

    override suspend fun getPopularMovies(page: Int): Either<CallError, ResponseList<MovieResponse>> {
        return movieApi.getPopularMovies(page)
    }

    override suspend fun searchMovie(
        query: String,
        page: Int
    ): Either<CallError, ResponseList<MovieResponse>> {
        return movieApi.searchMovie(query, page)
    }
}