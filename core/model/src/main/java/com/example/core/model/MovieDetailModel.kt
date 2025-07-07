package com.example.core.model


data class MovieDetailModel(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val genreResponses: List<GenreModel>?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val status: String,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int
)

