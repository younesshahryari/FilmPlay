package com.example.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    @SerialName( "id") val id: Int,
    @SerialName( "adult") val adult: Boolean,
    @SerialName( "backdrop_path") val backdropPath: String?,
    @SerialName( "genres") val genreResponses: List<GenreResponse>?,
    @SerialName( "overview") val overview: String?,
    @SerialName( "poster_path") val posterPath: String?,
    @SerialName( "release_date") val releaseDate: String,
    @SerialName( "status") val status: String,
    @SerialName( "title") val title: String,
    @SerialName( "vote_average") val voteAverage: Float,
    @SerialName( "vote_count") val voteCount: Int
)

