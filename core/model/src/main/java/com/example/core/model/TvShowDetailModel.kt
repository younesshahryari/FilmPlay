package com.example.core.model

data class TvShowDetailModel(
    val adult: Boolean,
    val backdropPath: String?,
    val firstAirDate: String,
    val genreResponses: List<GenreModel>?,
    val homepage: String,
    val id: Int,
    val inProduction: Boolean,
    val lastAirDate: String,
    val lastEpisodeModelToAir: EpisodeModel?,
    val name: String,
    val nextEpisodeModelToAir: EpisodeModel?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String?,
    val seasonModels: List<SeasonModel>?,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Float,
    val voteCount: Int
)
