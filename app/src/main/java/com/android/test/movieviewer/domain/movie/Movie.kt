package com.android.test.movieviewer.domain.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Movie(
    val id: MovieId,
    val title: String
)

data class MovieDetails(
    val id: MovieId,
    val title: String,
    val collectionName: String,
    val movieCollection: List<Movie>
)

@Parcelize
data class MovieId(
    val value: String
) : Parcelable
