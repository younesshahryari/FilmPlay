package com.aparat.androidinterview.data.repository.movie

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.persentation.model.ListModel
import com.aparat.androidinterview.persentation.model.MovieModel

interface MovieRepository {
    suspend fun getMovie(
        movieId: Int
    ): Either<CallError, MovieModel>

    suspend fun getPopularMovies(
        page: Int
    ): Either<CallError, ListModel<MovieModel>>

    suspend fun searchMovie(
        query: String,
        page: Int
    ): Either<CallError, ListModel<MovieModel>>

}