package com.android.test.movieviewer.data.movie.api

import com.google.gson.annotations.SerializedName

data class ApiDataMoviesNowPlayingResponse(
    @SerializedName("results")
    val movies: List<ApiDataMovieNowPlaying>
)

data class ApiDataMovieNowPlaying(
    val title: String
)