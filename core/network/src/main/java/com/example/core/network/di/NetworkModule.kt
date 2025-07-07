package com.example.core.network.di

import com.example.core.network.api.movie.MovieApi
import com.example.core.network.api.movie.MovieApiImpl
import com.example.core.network.api.tvshow.TvShowApi
import com.example.core.network.api.tvshow.TvShowApiImpl
import com.example.core.network.client.AndroidBaseKtorClientBuilder
import com.example.core.network.client.KtorClientBuilder
import com.example.core.network.datasource.movie.MovieRemoteDataSource
import com.example.core.network.datasource.movie.MovieRemoteDataSourceImpl
import com.example.core.network.datasource.tvshow.TvShowRemoteDataSource
import com.example.core.network.datasource.tvshow.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideKtorClientBuilder(): HttpClient = AndroidBaseKtorClientBuilder().buildKtorClient()

    @Provides
    fun provideMovieApi(client: HttpClient): MovieApi = MovieApiImpl(client)

    @Provides
    fun provideTvShowApi(client: HttpClient): TvShowApi = TvShowApiImpl(client)

    @Provides
    fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieApi)

    @Provides
    fun provideTvShowRemoteDataSource(tvShowApi: TvShowApi): TvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(tvShowApi)

}
