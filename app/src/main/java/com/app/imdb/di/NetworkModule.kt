package com.app.imdb.di

import com.app.imdb.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        moshiConverter: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(moshiConverter)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("http_logging") httpLoggingInterceptor: Interceptor,
        @Named("imdb_api_key") apiKeyInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    @Singleton
    @Named("imdb_api_key")
    fun provideQueryInterceptor(): Interceptor = Interceptor {
        val urlBuilder = it.request().url.newBuilder()
            .addQueryParameter("api_key", "38a73d59546aa378980a88b645f487fc")
        val request = it.request().newBuilder().url(urlBuilder.build())
        it.proceed(request.build())
    }

    @Provides
    @Singleton
    @Named("http_logging")
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): Converter.Factory = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun provideBaseUrl() = "https://api.themoviedb.org"

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}