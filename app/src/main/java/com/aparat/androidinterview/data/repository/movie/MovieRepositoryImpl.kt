package com.aparat.androidinterview.data.repository.movie

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.data.remoteData.dataSource.movie.MovieRemoteDataSource
import com.aparat.androidinterview.persentation.model.ListModel
import com.aparat.androidinterview.persentation.model.MovieModel
import com.aparat.androidinterview.persentation.model.toMovieListModel
import com.aparat.androidinterview.persentation.model.toMovieModel

class MovieRepositoryImpl(private val movieRemoteDataSource: MovieRemoteDataSource) :
    MovieRepository {
    override suspend fun getMovie(movieId: Int): Either<CallError, MovieModel> {
        return movieRemoteDataSource.getMovie(movieId).map { it.toMovieModel() }
    }

    override suspend fun getPopularMovies(page: Int): Either<CallError, ListModel<MovieModel>> {
        return movieRemoteDataSource.getPopularMovies(page).map { it.toMovieListModel() }
    }

    override suspend fun searchMovie(
        query: String,
        page: Int
    ): Either<CallError, ListModel<MovieModel>> {
        return movieRemoteDataSource.searchMovie(query, page).map { it.toMovieListModel() }
    }


}