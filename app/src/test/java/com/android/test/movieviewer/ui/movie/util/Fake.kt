package com.android.test.movieviewer.ui.movie.util

import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.movie.MovieCollection
import com.android.test.movieviewer.domain.movie.MovieDetails
import com.android.test.movieviewer.domain.movie.MovieId

class Fake {

    companion object {

        val EMOJI_MOVIE = Movie(
            MovieId("emoji"),
            "The Emoji Movie",
            "https://image.tmdb.org/t/p/original/emoji-url"
        )

        val EMOJI_MOVIE2 = Movie(
            MovieId("emoji2"),
            "The Emoji Movie II",
            "https://image.tmdb.org/t/p/original/emoji-url2"
        )

        val EMOJI_MOVIE_DETAILS = MovieDetails(
            MovieId("emoji"),
            "The Emoji Movie",
            MovieCollection(
                "The Emos",
                listOf(
                    EMOJI_MOVIE,
                    EMOJI_MOVIE2
                )
            )
        )

        val EMOJI_MOVIE_DETAILS_NO_COLLECTION = MovieDetails(
            MovieId("emoji"),
            "The Emoji Movie",
            null
        )

    }

}