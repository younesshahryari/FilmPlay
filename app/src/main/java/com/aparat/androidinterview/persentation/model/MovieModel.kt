package com.aparat.androidinterview.persentation.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class MovieModel(
    override val id: Int,
    override val title: String,
    override val date: String,
    override val genres: List<Int>?,
    override val thumbnail: String?,
    override val voteAverage: Float,
) : MediaModel