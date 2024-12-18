package com.aparat.androidinterview.persentation.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class TvShowModel(
    val id: Int,
    val title: String,
    val date: String,
    val genres: List<Int>?,
    val thumbnail: String?,
    val voteAverage: Float,
)
