package com.example.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowResponse(
    @SerialName( "id") val id: Int,
    @SerialName( "name") val title: String,
    @SerialName( "first_air_date") val date: String,
    @SerialName( "genre_ids") val genres: List<Int>?,
    @SerialName( "poster_path") val thumbnail: String?,
    @SerialName( "vote_average") val voteAverage: Float,
)
