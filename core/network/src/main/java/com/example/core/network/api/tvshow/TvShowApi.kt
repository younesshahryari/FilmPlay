package com.example.core.network.api.tvshow

import com.example.core.network.model.ResponseList
import com.example.core.network.model.TvShowDetailResponse
import com.example.core.network.model.TvShowResponse

interface TvShowApi {
    suspend fun getTvShow(tvShowId: Int): TvShowDetailResponse
    suspend fun getPopularTvShow(page: Int): ResponseList<TvShowResponse>
    suspend fun searchTvShow(query: String, page: Int): ResponseList<TvShowResponse>
}