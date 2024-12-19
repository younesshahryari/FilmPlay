package com.aparat.androidinterview.di

import com.aparat.androidinterview.data.remoteData.dataSource.movie.MovieRemoteDataSource
import com.aparat.androidinterview.data.remoteData.dataSource.movie.MovieRemoteDataSourceImpl
import com.aparat.androidinterview.data.remoteData.dataSource.tvShow.TvShowRemoteDataSource
import com.aparat.androidinterview.data.remoteData.dataSource.tvShow.TvShowRemoteDataSourceImpl
import com.aparat.androidinterview.data.remoteData.service.MovieApi
import com.aparat.androidinterview.data.remoteData.service.TvShowApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieApi)

    @Provides
    fun provideTvShowRemoteDataSource(tvShowApi: TvShowApi): TvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(tvShowApi)
}
