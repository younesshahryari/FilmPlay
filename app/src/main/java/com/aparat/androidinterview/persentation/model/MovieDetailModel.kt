package com.aparat.androidinterview.persentation.model

import com.aparat.androidinterview.data.remoteData.model.GenreResponse

data class MovieDetailModel(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val genreResponses: List<GenreResponse>?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val status: String,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int
)

