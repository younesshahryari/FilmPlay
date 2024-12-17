package com.aparat.androidinterview.persentation.model

import com.aparat.androidinterview.data.remoteData.model.MovieResponse
import com.aparat.androidinterview.data.remoteData.model.ResponseList
import com.aparat.androidinterview.data.remoteData.model.TvShowResponse

fun MovieResponse.toMovieModel(): MovieModel {
    return MovieModel(
        id = id,
        title = title,
        date = date,
        genres = genres,
        thumbnail = thumbnail,
        voteAverage = voteAverage
    )
}

fun ResponseList<MovieResponse>.toMovieListModel(): ListModel<MovieModel> {
    return ListModel(
        page = page,
        results = results.map { it.toMovieModel() },
        totalPages = totalPages,
    )
}

fun ResponseList<TvShowResponse>.toTvShowListModel(): ListModel<TvShowModel> {
    return ListModel(
        page = page,
        results = results.map { it.toTvShowModel() },
        totalPages = totalPages,
    )
}

fun TvShowResponse.toTvShowModel(): TvShowModel {
    return TvShowModel(
        id = id,
        title = title,
        date = date,
        genres = genres,
        thumbnail = thumbnail,
        voteAverage = voteAverage
    )
}

