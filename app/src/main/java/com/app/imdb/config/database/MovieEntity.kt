package com.app.imdb.config.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
data class MovieEntity(
    val adult: Boolean,
    val backdrop_path: String,
    @ColumnInfo(name = "genre_ids")
    val genreIds: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int
)