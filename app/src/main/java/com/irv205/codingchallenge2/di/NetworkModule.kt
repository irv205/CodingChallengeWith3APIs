package com.irv205.codingchallenge2.di

import com.irv205.codingchallenge2.BuildConfig
import com.irv205.codingchallenge2.data.networkdatasource.service.AnimeService
import com.irv205.codingchallenge2.data.networkdatasource.service.NewsService
import com.irv205.codingchallenge2.data.networkdatasource.service.SimpsonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    @ProvideAnime
    fun provideRetrofitAnime(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL_ANIME)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideAnimeService(@ProvideAnime retrofit: Retrofit): AnimeService = retrofit.create(AnimeService::class.java)

    @Singleton
    @Provides
    @ProvideNews
    fun provideRetrofitNews(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideNewsService(@ProvideNews retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Singleton
    @Provides
    @ProvideSimpsons
    fun provideRetrofitSimpson(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL_SIMPSON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideSimpsonService(@ProvideSimpsons retrofit: Retrofit): SimpsonService = retrofit.create(SimpsonService::class.java)

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class ProvideAnime

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class ProvideNews

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class ProvideSimpsons

}