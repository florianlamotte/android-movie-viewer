package com.android.test.movieviewer.domain.movie

import com.android.test.movieviewer.domain.util.Response

interface MovieRepository {

    fun getMovies(): Response<List<Movie>>

}