package com.app.imdb

import com.app.imdb.model.MovieListApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("https://api.themoviedb.org/3/movie/popular")
    suspend fun fetchMovieList(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieListApiResponse>
}