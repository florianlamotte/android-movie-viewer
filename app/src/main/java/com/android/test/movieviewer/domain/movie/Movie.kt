package com.android.test.movieviewer.domain.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Movie(
    val id: MovieId,
    val title: String,
    val imageUrl: String
)

data class MovieDetails(
    val id: MovieId,
    val title: String,
    val collection: MovieCollection?
)

data class MovieCollection(
    val collectionName: String,
    val movieCollection: List<Movie>
)

@Parcelize
data class MovieId(
    val value: String
) : Parcelable
