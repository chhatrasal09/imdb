package com.app.imdb.model

import android.os.Parcelable
import android.text.style.RelativeSizeSpan
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable {
    val releaseDateSpan
        get() = buildSpannedString {
            append("Release Date\n")
            inSpans(RelativeSizeSpan(0.75f)) {
                append(release_date)
            }
        }
    val voteAverageSpan
        get() = buildSpannedString {
            append("Rating\n")
            inSpans(RelativeSizeSpan(0.75f)) {
                append("${vote_average.toFloat()}")
            }
        }
    val popularitySpan
        get() = buildSpannedString {
            append("Popularity\n")
            inSpans(RelativeSizeSpan(0.75f)) {
                append("$popularity")
            }
        }
}