package com.android.test.movieviewer.data.movie.api

import com.google.gson.annotations.SerializedName

data class ApiDataMoviesNowPlayingResponse(
    @SerializedName("results")
    val movies: List<ApiDataMovieNowPlaying>
)

data class ApiDataMovieNowPlaying(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String
)