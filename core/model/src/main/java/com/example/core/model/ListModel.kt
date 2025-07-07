package com.example.core.model

data class ListModel<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
)
