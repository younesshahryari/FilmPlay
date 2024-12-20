package com.aparat.androidinterview.persentation.model

interface MediaModel {
    val id: Int
    val title: String
    val date: String
    val genres: List<Int>?
    val thumbnail: String?
    val voteAverage: Float
}
