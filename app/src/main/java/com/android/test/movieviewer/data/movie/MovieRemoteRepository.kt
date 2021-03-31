package com.android.test.movieviewer.data.movie

import com.android.test.movieviewer.data.movie.api.ApiTheMovieDb
import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.movie.MovieRepository
import com.android.test.movieviewer.domain.util.Response
import javax.inject.Inject

class MovieRemoteRepository @Inject constructor(
    private val theMovieDbAPi: ApiTheMovieDb
) : MovieRepository {

    override suspend fun getMovies(): Response<List<Movie>> {
        return try {
            val moviesNowPlaying = theMovieDbAPi.moviesNowPlaying().movies.map {
                Movie(
                    MovieId(it.id),
                    it.title
                )
            }
            Response.Success(moviesNowPlaying)
        } catch (e: Exception) {
            Response.Error
        }
    }

}