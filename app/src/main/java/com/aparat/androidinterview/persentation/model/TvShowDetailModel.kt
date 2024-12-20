package com.aparat.androidinterview.persentation.model

data class TvShowDetailModel(
    val adult: Boolean,
    val backdropPath: String?,
    val firstAirDate: String,
    val genreResponses: List<GenreModel>?,
    val homepage: String,
    val id: Int,
    val inProduction: Boolean,
    val lastAirDate: String,
    val lastEpisodeResponseToAir: EpisodeModel?,
    val name: String,
    val nextEpisodeResponseToAir: EpisodeModel?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String?,
    val seasonResponses: List<SeasonModel>?,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Float,
    val voteCount: Int
)
