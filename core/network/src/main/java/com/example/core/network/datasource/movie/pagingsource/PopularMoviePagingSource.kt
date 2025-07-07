package com.example.core.network.datasource.movie.pagingsource

import android.net.http.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.network.api.movie.MovieApi
import com.example.core.network.model.MovieResponse
import okio.IOException
import javax.inject.Inject

class PopularMoviePagingSource @Inject constructor(
    private val apiService: MovieApi,
) : PagingSource<Int, MovieResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val position = params.key ?: START_PAGE_NUMBER
        return try {
            val response = apiService.getPopularMovies(position)
            val movies = response.results
            LoadResult.Page(
                data = movies,
                prevKey = if (position == START_PAGE_NUMBER) null else response.page - 1,
                nextKey = if (movies.isEmpty() || position == response.totalPages) null else response.page + 1
            )
        } catch (exception: Exception) {
            println(exception)
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
        const val START_PAGE_NUMBER = 1
    }
}