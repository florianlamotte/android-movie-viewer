package com.android.test.movieviewer.domain.movie.usecase

import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.util.Response
import javax.inject.Inject

class GetMovieById @Inject constructor() {

    suspend fun execute(): Response<Movie> {
        TODO()
    }

}
