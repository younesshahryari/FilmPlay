package com.aparat.androidinterview.di

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.aparat.androidinterview.BuildConfig
import com.aparat.androidinterview.data.remoteData.dataSource.movie.MovieRemoteDataSource
import com.aparat.androidinterview.data.remoteData.dataSource.movie.MovieRemoteDataSourceImpl
import com.aparat.androidinterview.data.remoteData.dataSource.tvShow.TvShowRemoteDataSource
import com.aparat.androidinterview.data.remoteData.dataSource.tvShow.TvShowRemoteDataSourceImpl
import com.aparat.androidinterview.data.remoteData.service.AuthInterceptor
import com.aparat.androidinterview.data.remoteData.service.MovieApi
import com.aparat.androidinterview.data.remoteData.service.TvShowApi
import com.aparat.androidinterview.data.repository.movie.MovieRepository
import com.aparat.androidinterview.data.repository.movie.MovieRepositoryImpl
import com.aparat.androidinterview.data.repository.tvShow.TvShowRepository
import com.aparat.androidinterview.data.repository.tvShow.TvShowRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    )

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create()

    @Provides
    fun provideTvShowApi(retrofit: Retrofit): TvShowApi = retrofit.create()

    @Provides
    fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieApi)

    @Provides
    fun provideTvShowRemoteDataSource(tvShowApi: TvShowApi): TvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(tvShowApi)

    @Provides
    fun provideMovieRepository(movieRemoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource)

    @Provides
    fun provideTvShowRepository(tvShowRemoteDataSource: TvShowRemoteDataSource): TvShowRepository =
        TvShowRepositoryImpl(tvShowRemoteDataSource)
}
