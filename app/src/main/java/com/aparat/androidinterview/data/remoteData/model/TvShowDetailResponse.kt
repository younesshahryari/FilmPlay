package com.aparat.androidinterview.data.remoteData.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvShowDetailResponse(
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "first_air_date") val firstAirDate: String,
    @Json(name = "genres") val genreResponses: List<GenreResponse>?,
    @Json(name = "homepage") val homepage: String,
    @Json(name = "id") val id: Int,
    @Json(name = "in_production") val inProduction: Boolean,
    @Json(name = "last_air_date") val lastAirDate: String,
    @Json(name = "last_episode_to_air") val lastEpisodeResponseToAir: EpisodeResponse?,
    @Json(name = "name") val name: String,
    @Json(name = "next_episode_to_air") val nextEpisodeResponseToAir: EpisodeResponse?,
    @Json(name = "number_of_episodes") val numberOfEpisodes: Int,
    @Json(name = "number_of_seasons") val numberOfSeasons: Int,
    @Json(name = "origin_country") val originCountry: List<String>,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_name") val originalName: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Float,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "seasons") val seasonResponses: List<SeasonResponse>?,
    @Json(name = "status") val status: String,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "type") val type: String,
    @Json(name = "vote_average") val voteAverage: Float,
    @Json(name = "vote_count") val voteCount: Int
)

