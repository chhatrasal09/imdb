package com.app.imdb.di

import com.app.imdb.repo.MovieRepo
import com.app.imdb.repo.MovieRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieModule {

    @Binds
    abstract fun bindMovieRepo(impl: MovieRepoImpl): MovieRepo
}