package com.android.test.movieviewer.domain.movie.usecase

import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.movie.MovieRepository
import com.android.test.movieviewer.domain.util.Response
import javax.inject.Inject

class GetMovies @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun execute(): Response<List<Movie>> {
        return movieRepository.getMovies()
    }

}
