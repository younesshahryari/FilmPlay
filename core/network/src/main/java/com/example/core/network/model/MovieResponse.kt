package com.example.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("release_date") val date: String,
    @SerialName("genre_ids") val genres: List<Int>?,
    @SerialName("poster_path") val thumbnail: String?,
    @SerialName("vote_average") val voteAverage: Float,
)
