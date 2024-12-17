package com.aparat.androidinterview.data.repository.tvShow

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.data.remoteData.dataSource.tvShow.TvShowRemoteDataSource
import com.aparat.androidinterview.persentation.model.ListModel
import com.aparat.androidinterview.persentation.model.TvShowModel
import com.aparat.androidinterview.persentation.model.toMovieListModel
import com.aparat.androidinterview.persentation.model.toTvShowListModel
import com.aparat.androidinterview.persentation.model.toTvShowModel

class TvShowRepositoryImpl(private val tvShowRemoteDataSource: TvShowRemoteDataSource) :
    TvShowRepository {
    override suspend fun getTvShow(tvId: Int): Either<CallError, TvShowModel> {
        return tvShowRemoteDataSource.getTvShow(tvId).map { it.toTvShowModel() }
    }

    override suspend fun getPopularTvShows(page: Int): Either<CallError, ListModel<TvShowModel>> {
        return tvShowRemoteDataSource.getPopularTvShows(page).map { it.toTvShowListModel() }
    }

    override suspend fun searchTvShows(
        query: String,
        page: Int
    ): Either<CallError, ListModel<TvShowModel>> {
        return tvShowRemoteDataSource.searchTvShows(query, page).map { it.toTvShowListModel() }
    }

}