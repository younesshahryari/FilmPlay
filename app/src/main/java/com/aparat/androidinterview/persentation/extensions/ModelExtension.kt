package com.aparat.androidinterview.persentation.extensions

import com.aparat.androidinterview.data.remoteData.model.EpisodeResponse
import com.aparat.androidinterview.data.remoteData.model.GenreResponse
import com.aparat.androidinterview.data.remoteData.model.MovieDetailResponse
import com.aparat.androidinterview.data.remoteData.model.MovieResponse
import com.aparat.androidinterview.data.remoteData.model.ResponseList
import com.aparat.androidinterview.data.remoteData.model.SeasonResponse
import com.aparat.androidinterview.data.remoteData.model.TvShowDetailResponse
import com.aparat.androidinterview.data.remoteData.model.TvShowResponse
import com.aparat.androidinterview.persentation.model.EpisodeModel
import com.aparat.androidinterview.persentation.model.GenreModel
import com.aparat.androidinterview.persentation.model.ListModel
import com.aparat.androidinterview.persentation.model.MovieDetailModel
import com.aparat.androidinterview.persentation.model.MovieModel
import com.aparat.androidinterview.persentation.model.SeasonModel
import com.aparat.androidinterview.persentation.model.TvShowDetailModel
import com.aparat.androidinterview.persentation.model.TvShowModel

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

fun MovieDetailResponse.toMovieDetailModel(): MovieDetailModel {
    return MovieDetailModel(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreResponses = genreResponses,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        status = status,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
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

fun TvShowDetailResponse.toTvShowDetailModel(): TvShowDetailModel {
    return TvShowDetailModel(
        id = id,
        name = name,
        adult = adult,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        genreResponses = genreResponses?.map { it.toGenreModel() },
        homepage = homepage,
        inProduction = inProduction,
        lastAirDate = lastAirDate,
        lastEpisodeModelToAir = lastEpisodeResponseToAir?.toEpisodeModel(),
        nextEpisodeModelToAir = nextEpisodeResponseToAir?.toEpisodeModel(),
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        seasonModels = seasonResponses?.map { it.toSeasonModel() },
        status = status,
        tagline = tagline,
        type = type,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun GenreResponse.toGenreModel(): GenreModel {
    return GenreModel(
        id = id,
        name = name
    )
}

fun EpisodeResponse.toEpisodeModel(): EpisodeModel {
    return EpisodeModel(
        id = id,
        name = name,
        overview = overview,
        voteAverage = voteAverage,
        voteCount = voteCount,
        airDate = airDate,
        episodeNumber = episodeNumber,
        episodeType = episodeType,
        productionCode = productionCode,
        runtime = runtime,
        seasonNumber = seasonNumber,
        showId = showId,
        stillPath = stillPath
    )
}

fun SeasonResponse.toSeasonModel(): SeasonModel {
    return SeasonModel(
        id = id,
        name = name,
        airDate = airDate,
        episodeCount = episodeCount,
        overview = overview,
        posterPath = posterPath,
        seasonNumber = seasonNumber,
        voteAverage = voteAverage
    )
}


