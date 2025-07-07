package com.example.core.data.di

import com.example.core.data.repository.movie.MovieRepository
import com.example.core.data.repository.movie.MovieRepositoryImpl
import com.example.core.data.repository.tvShow.TvShowRepository
import com.example.core.data.repository.tvShow.TvShowRepositoryImpl
import com.example.core.network.datasource.movie.MovieRemoteDataSource
import com.example.core.network.datasource.tvshow.TvShowRemoteDataSource
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
