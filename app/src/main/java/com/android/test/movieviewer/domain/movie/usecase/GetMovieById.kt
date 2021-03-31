package com.android.test.movieviewer.domain.movie.usecase

import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.movie.MovieRepository
import com.android.test.movieviewer.domain.util.Response
import javax.inject.Inject

class GetMovieById @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(
        id: MovieId
    ): Response<Movie> {
        return movieRepository.getMovieById(id)
    }

}
