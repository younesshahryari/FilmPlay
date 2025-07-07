package com.example.core.network.api.movie

import com.example.core.network.model.MovieDetailResponse
import com.example.core.network.model.MovieResponse
import com.example.core.network.model.ResponseList

interface MovieApi {
    suspend fun getMovie(movieId: Int): MovieDetailResponse
    suspend fun getPopularMovies(page: Int): ResponseList<MovieResponse>
    suspend fun searchMovie(query: String, page: Int): ResponseList<MovieResponse>
}