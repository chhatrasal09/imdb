package com.app.imdb.di

import android.content.Context
import androidx.room.Room
import com.app.imdb.config.database.AppDatabase
import com.app.imdb.config.database.IntListTypeConverter
import com.app.imdb.config.database.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "imdb_app")
            .addTypeConverter(IntListTypeConverter::class)
            .build()

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao()
}