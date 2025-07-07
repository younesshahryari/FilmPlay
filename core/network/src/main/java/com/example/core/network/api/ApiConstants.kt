package com.example.core.network.api

object ApiConstants {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    //Movie
    const val GET_MOVIE_URL = BASE_URL + "movie/"
    const val GET_POPULAR_MOVIE_URL = BASE_URL + "movie/popular"
    const val GET_SEARCH_MOVIE_URL = BASE_URL + "search/movie"

    //tvShow
    const val GET_TV_SHOW_URL = BASE_URL + "tv/"
    const val GET_POPULAR_TV_SHOW_URL = BASE_URL + "tv/popular"
    const val GET_SEARCH_TV_SHOW_URL = BASE_URL + "search/tv"

}