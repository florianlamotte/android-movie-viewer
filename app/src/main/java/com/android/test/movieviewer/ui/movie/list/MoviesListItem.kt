package com.android.test.movieviewer.ui.movie.list

import com.android.test.movieviewer.domain.movie.MovieId

data class MoviesListItem(
    val id: MovieId,
    val title: String,
    val imageUrl: String
)