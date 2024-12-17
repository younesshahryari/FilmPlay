package com.aparat.androidinterview.persentation.model

data class ListModel<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
)
