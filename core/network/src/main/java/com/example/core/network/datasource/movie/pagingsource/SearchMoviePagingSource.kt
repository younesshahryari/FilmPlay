package com.example.core.network.datasource.movie.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.network.api.movie.MovieApi
import com.example.core.network.model.MovieResponse
import javax.inject.Inject

class SearchMoviePagingSource @Inject constructor(
    private val searchQuery: String,
    private val apiService: MovieApi,
) : PagingSource<Int, MovieResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val position = params.key ?: START_PAGE_NUMBER
        return try {
            val response = apiService.searchMovie(query = searchQuery, page = position)
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

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val START_PAGE_NUMBER = 0
    }
}