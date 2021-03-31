package com.android.test.movieviewer.ui.movie.util

import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.movie.MovieDetails
import com.android.test.movieviewer.domain.movie.MovieId

class Fake {

    companion object {

        val EMOJI_MOVIE = Movie(
            MovieId("emoji"),
            "The Emoji Movie"
        )

        val EMOJI_MOVIE2 = Movie(
            MovieId("emoji2"),
            "The Emoji Movie II"
        )

        val EMOJI_MOVIE_DETAILS = MovieDetails(
            MovieId("emoji"),
            "The Emoji Movie",
            "The Emos",
            listOf(
                EMOJI_MOVIE,
                EMOJI_MOVIE2
            )
        )

    }

}