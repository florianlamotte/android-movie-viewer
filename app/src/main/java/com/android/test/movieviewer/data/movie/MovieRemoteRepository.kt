package com.android.test.movieviewer.data.movie

import com.android.test.movieviewer.data.movie.api.ApiTheMovieDb
import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.movie.MovieRepository
import com.android.test.movieviewer.domain.util.Response

class MovieRemoteRepository(
    private val theMovieDbAPi: ApiTheMovieDb
): MovieRepository {

    override fun getMovies(): Response<List<Movie>> {
        return Response.Error
    }

}