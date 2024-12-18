package com.aparat.androidinterview.persentation.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class MovieModel(
    val id: Int,
    val title: String,
    val date: String,
    val genres: List<Int>?,
    val thumbnail: String?,
    val voteAverage: Float,
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieModel

        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}
