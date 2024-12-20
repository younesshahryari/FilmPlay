package com.aparat.androidinterview.persentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class TvShowModel(
    override val id: Int,
    override val title: String,
    override val date: String,
    override val genres: List<Int>?,
    override val thumbnail: String?,
    override val voteAverage: Float,
) : MediaModel