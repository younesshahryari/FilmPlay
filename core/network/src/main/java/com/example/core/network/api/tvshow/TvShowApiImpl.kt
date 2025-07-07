package com.example.core.network.api.tvshow

import com.example.core.network.api.ApiConstants
import com.example.core.network.model.ResponseList
import com.example.core.network.model.TvShowDetailResponse
import com.example.core.network.model.TvShowResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowApiImpl @Inject constructor(
    private val client: HttpClient,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TvShowApi {

    override suspend fun getTvShow(tvShowId: Int): TvShowDetailResponse = withContext(ioDispatcher) {
        return@withContext client.get("${ApiConstants.GET_TV_SHOW_URL}$tvShowId").body()
    }

    override suspend fun getPopularTvShow(page: Int): ResponseList<TvShowResponse> =
        withContext(ioDispatcher) {
            return@withContext client.get(ApiConstants.GET_POPULAR_TV_SHOW_URL) {
                parameter("page", page)
            }.body()
        }

    override suspend fun searchTvShow(query: String, page: Int): ResponseList<TvShowResponse> =
        withContext(ioDispatcher) {
            return@withContext client.get(ApiConstants.GET_SEARCH_TV_SHOW_URL) {
                parameter("query", query)
                parameter("page", page)
            }.body()
        }
}