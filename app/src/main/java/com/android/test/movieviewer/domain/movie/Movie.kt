package com.android.test.movieviewer.domain.movie

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

data class MovieId(
    val value: String
)
