package com.example.core.network.api.movie

import com.example.core.network.api.ApiConstants
import com.example.core.network.model.MovieDetailResponse
import com.example.core.network.model.MovieResponse
import com.example.core.network.model.ResponseList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieApiImpl @Inject constructor(
    private val client: HttpClient,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieApi {

    override suspend fun getMovie(movieId: Int): MovieDetailResponse = withContext(ioDispatcher) {
        return@withContext client.get("${ApiConstants.GET_MOVIE_URL}$movieId").body()
    }

    override suspend fun getPopularMovies(page: Int): ResponseList<MovieResponse> =
        withContext(ioDispatcher) {
            return@withContext client.get(ApiConstants.GET_POPULAR_MOVIE_URL) {
                parameter("page", page)
            }.body()
        }

    override suspend fun searchMovie(query: String, page: Int): ResponseList<MovieResponse> =
        withContext(ioDispatcher) {
            return@withContext client.get(ApiConstants.GET_SEARCH_MOVIE_URL) {
                parameter("query", query)
                parameter("page", page)
            }.body()
        }
}