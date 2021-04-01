package com.android.test.movieviewer.data.movie.api

import com.google.gson.annotations.SerializedName

data class ApiDataMoviesNowPlayingResponse(
    @SerializedName("results")
    val movies: List<ApiDataMovieNowPlaying>,
)

data class ApiDataMovieNowPlaying(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val imageUrl: String
)

data class ApiDataMovieResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("belongs_to_collection")
    val collection: ApiDataMovieCollection?,
)

data class ApiDataMovieCollection(
    @SerializedName("id")
    val collectionId: String,
    @SerializedName("name")
    val collectionName: String,
)

data class ApiDataCollection(
    @SerializedName("id")
    val id: String,
    @SerializedName("parts")
    val parts: List<ApiDataCollectionParts>,
)

data class ApiDataCollectionParts(
    @SerializedName("id")
    val movieId: String,
    @SerializedName("title")
    val movieTitle: String,
    @SerializedName("poster_path")
    val imageUrl: String
)
