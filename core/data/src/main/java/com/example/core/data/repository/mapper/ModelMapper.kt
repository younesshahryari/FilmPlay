package com.example.core.data.repository.mapper

import com.example.core.data.POSTER_BASE_URL
import com.example.core.data.THUMBNAIL_BASE_URL
import com.example.core.model.EpisodeModel
import com.example.core.model.GenreModel
import com.example.core.model.ListModel
import com.example.core.model.MovieDetailModel
import com.example.core.model.MovieModel
import com.example.core.model.SeasonModel
import com.example.core.model.TvShowDetailModel
import com.example.core.model.TvShowModel
import com.example.core.network.model.EpisodeResponse
import com.example.core.network.model.GenreResponse
import com.example.core.network.model.MovieDetailResponse
import com.example.core.network.model.MovieResponse
import com.example.core.network.model.ResponseList
import com.example.core.network.model.SeasonResponse
import com.example.core.network.model.TvShowDetailResponse
import com.example.core.network.model.TvShowResponse

fun MovieResponse.asExternalModel(): MovieModel {
    return MovieModel(
        id = id,
        title = title,
        date = date,
        genres = genres,
        thumbnail = THUMBNAIL_BASE_URL + thumbnail,
        voteAverage = voteAverage
    )
}

fun MovieDetailResponse.asExternalModel(): MovieDetailModel {
    return MovieDetailModel(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreResponses = genreResponses?.map { it.asExternalModel() },
        overview = overview,
        posterPath = POSTER_BASE_URL + posterPath,
        releaseDate = releaseDate,
        status = status,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}


fun ResponseList<MovieResponse>.asExternalModel(): ListModel<MovieModel> {
    return ListModel(
        page = page,
        results = results.map { it.asExternalModel() },
        totalPages = totalPages,
    )
}

fun TvShowResponse.asExternalModel(): TvShowModel {
    return TvShowModel(
        id = id,
        title = title,
        date = date,
        genres = genres,
        thumbnail = THUMBNAIL_BASE_URL + thumbnail,
        voteAverage = voteAverage
    )
}

fun TvShowDetailResponse.asExternalModel(): TvShowDetailModel {
    return TvShowDetailModel(
        id = id,
        name = name,
        adult = adult,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        genreResponses = genreResponses?.map { it.asExternalModel() },
        homepage = homepage,
        inProduction = inProduction,
        lastAirDate = lastAirDate,
        lastEpisodeModelToAir = lastEpisodeResponseToAir?.asExternalModel(),
        nextEpisodeModelToAir = nextEpisodeResponseToAir?.asExternalModel(),
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = POSTER_BASE_URL + posterPath,
        seasonModels = seasonResponses?.map { it.asExternalModel() },
        status = status,
        tagline = tagline,
        type = type,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun GenreResponse.asExternalModel(): GenreModel {
    return GenreModel(
        id = id,
        name = name
    )
}

fun EpisodeResponse.asExternalModel(): EpisodeModel {
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

fun SeasonResponse.asExternalModel(): SeasonModel {
    return SeasonModel(
        id = id,
        name = name,
        airDate = airDate,
        episodeCount = episodeCount,
        overview = overview,
        posterPath = POSTER_BASE_URL + posterPath,
        seasonNumber = seasonNumber,
        voteAverage = voteAverage
    )
}


