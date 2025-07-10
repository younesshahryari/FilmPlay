package com.example.core.network.datasource.tvshow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.network.api.tvshow.TvShowApi
import com.example.core.network.datasource.tvshow.pagingsource.PopularTvShowPagingSource
import com.example.core.network.datasource.tvshow.pagingsource.SearchTvShowPagingSource
import com.example.core.network.model.TvShowDetailResponse
import com.example.core.network.model.TvShowResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowRemoteDataSourceImpl @Inject constructor(private val tvShowApi: TvShowApi) :
    TvShowRemoteDataSource {

    override suspend fun getTvShow(tvId: Int): TvShowDetailResponse {
        return tvShowApi.getTvShow(tvId)
    }

    override fun getPopularTvShows(pageConfig: PagingConfig): Flow<PagingData<TvShowResponse>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = { PopularTvShowPagingSource(apiService = tvShowApi) }
        ).flow
    }

    override fun searchTvShows(
        query: String,
        pageConfig: PagingConfig,
    ): Flow<PagingData<TvShowResponse>> {
        return Pager(
            config = pageConfig,
            pagingSourceFactory = {
                SearchTvShowPagingSource(
                    searchQuery = query,
                    apiService = tvShowApi
                )
            }
        ).flow
    }
}