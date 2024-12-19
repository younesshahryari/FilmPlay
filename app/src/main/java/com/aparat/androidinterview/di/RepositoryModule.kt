package com.aparat.androidinterview.di

import com.aparat.androidinterview.data.remoteData.dataSource.movie.MovieRemoteDataSource
import com.aparat.androidinterview.data.remoteData.dataSource.tvShow.TvShowRemoteDataSource
import com.aparat.androidinterview.data.repository.movie.MovieRepository
import com.aparat.androidinterview.data.repository.movie.MovieRepositoryImpl
import com.aparat.androidinterview.data.repository.tvShow.TvShowRepository
import com.aparat.androidinterview.data.repository.tvShow.TvShowRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepository(movieRemoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource)

    @Provides
    fun provideTvShowRepository(tvShowRemoteDataSource: TvShowRemoteDataSource): TvShowRepository =
        TvShowRepositoryImpl(tvShowRemoteDataSource)
}
