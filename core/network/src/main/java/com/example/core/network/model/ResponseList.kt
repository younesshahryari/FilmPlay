package com.example.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseList<T>(
    @SerialName( "page") val page: Int,
    @SerialName( "results") val results: List<T>,
    @SerialName( "total_pages") val totalPages: Int,
)
