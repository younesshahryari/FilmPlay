package com.example.core.network.datasource.tvshow.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.network.api.tvshow.TvShowApi
import com.example.core.network.model.TvShowResponse
import javax.inject.Inject

class PopularTvShowPagingSource @Inject constructor(
    private val apiService: TvShowApi,
) : PagingSource<Int, TvShowResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShowResponse> {
        val position = params.key ?: START_PAGE_NUMBER
        return try {
            val response = apiService.getPopularTvShow(position)
            val movies = response.results
            LoadResult.Page(
                data = movies,
                prevKey = if (position == START_PAGE_NUMBER) null else response.page - 1,
                nextKey = if (movies.isEmpty() || position == response.totalPages) null else response.page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TvShowResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val START_PAGE_NUMBER = 0
    }
}