package com.android.test.movieviewer.data.movie

import com.android.test.movieviewer.data.movie.api.*
import com.android.test.movieviewer.domain.movie.MovieCollection
import com.android.test.movieviewer.domain.movie.MovieDetails
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.util.Response
import com.android.test.movieviewer.ui.movie.util.Fake.Companion.EMOJI_MOVIE
import com.android.test.movieviewer.ui.movie.util.Fake.Companion.EMOJI_MOVIE2
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieRemoteRepositoryTest {

    private lateinit var movieRemoteRepository: MovieRemoteRepository

    @Mock
    private lateinit var api: ApiTheMovieDb

    @Before
    fun setUp() {
        movieRemoteRepository = MovieRemoteRepository(
            api
        )
    }

    // MOVIES
    // Given API returns error, we return an error response
    @Test
    fun `given movies API throws error, then return error response`() = runBlockingTest {
        given(api.moviesNowPlaying()).willThrow(RuntimeException("Some issue"))

        val actual = movieRemoteRepository.getMovies()

        assertEquals(Response.Error, actual)
    }

    // Given API returns success, we return a success response
    @Test
    fun `given movies API returns result, then return success response`() = runBlockingTest {
        given(api.moviesNowPlaying()).willReturn(
            ApiDataMoviesNowPlayingResponse(
                listOf(
                    ApiDataMovieNowPlaying("emoji", "The Emoji Movie")
                )
            )
        )

        val actual = movieRemoteRepository.getMovies()

        assertEquals(Response.Success(listOf(EMOJI_MOVIE)), actual)
    }

    // Given API returns error, we return an error response
    @Test
    fun `given movie details API throws error, then return error response`() = runBlockingTest {
        given(api.movie(any())).willThrow(RuntimeException("Some issue"))

        val actual = movieRemoteRepository.getMovieById(MovieId("some id"))

        assertEquals(Response.Error, actual)
    }

    // Given API returns success, we return a success response
    @Test
    fun `given movie details API returns result, then return success response`() = runBlockingTest {
        given(api.movie(any())).willReturn(
            ApiDataMovieResponse(
                "emoji",
                "The Emoji Movie",
                "overview here",
                ApiDataMovieCollection(
                    "emo3",
                    "the emos"
                )
            )
        )
        given(api.collection(any())).willReturn(
            ApiDataCollection(
                "emo3",
                listOf(
                    ApiDataCollectionParts("emoji", "The Emoji Movie"),
                    ApiDataCollectionParts("emoji2", "The Emoji Movie II"),
                )
            )
        )

        val actual: Response<MovieDetails> = movieRemoteRepository.getMovieById(MovieId("some id"))

        assertEquals(Response.Success(
            MovieDetails(
                MovieId("emoji"),
                "The Emoji Movie",
                MovieCollection(
                    "the emos",
                    listOf(EMOJI_MOVIE, EMOJI_MOVIE2)
                )
            )
        ), actual)
    }

    // Given API returns success with null collection, we return a success response
    @Test
    fun `given movie details API returns result with null collection, then return success response`() = runBlockingTest {
        given(api.movie(any())).willReturn(
            ApiDataMovieResponse(
                "emoji",
                "The Emoji Movie",
                "overview here",
                null
            )
        )

        val actual: Response<MovieDetails> = movieRemoteRepository.getMovieById(MovieId("some id"))

        assertEquals(Response.Success(
            MovieDetails(
                MovieId("emoji"),
                "The Emoji Movie",
                null
            )
        ), actual)
    }

}