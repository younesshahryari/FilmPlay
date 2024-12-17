package com.aparat.androidinterview.data.remoteData.dataSource.movie

import com.aparat.androidinterview.data.remoteData.service.MovieApi

class MovieRemoteDataSourceImpl(private val movieApi: MovieApi) :
    MovieRemoteDataSource , MovieApi by movieApi