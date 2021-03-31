package com.android.test.movieviewer.ui.movie.details

import com.android.test.movieviewer.domain.movie.MovieId

data class MovieDetailsItem(
    val title: String,
    val collectionName: String?,
    val CollectionItems: List<CollectionItem>?
)

data class CollectionItem(
    val id: MovieId,
    val title: String
)