package com.aparat.androidinterview.persentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class ListModel<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
)
