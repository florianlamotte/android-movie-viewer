package com.android.test.movieviewer.domain.movie

data class Movie(
    val id: MovieId,
    val title: String
)

data class MovieId(
    val value: String
)
