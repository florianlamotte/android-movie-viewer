package com.android.test.movieviewer.domain.movie

import com.android.test.movieviewer.domain.util.Response

interface MovieRepository {

    suspend fun getMovies(): Response<List<Movie>>
    suspend fun getMovieById(id: MovieId): Response<Movie>
}