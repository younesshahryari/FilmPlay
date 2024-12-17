package com.aparat.androidinterview.data.remoteData.dataSource.tvShow

import com.aparat.androidinterview.data.remoteData.service.TvShowApi

class TvShowRemoteDataSourceImpl(private val tvShowApi: TvShowApi) :
    TvShowRemoteDataSource, TvShowApi by tvShowApi