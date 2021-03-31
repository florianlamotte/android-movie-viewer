package com.android.test.movieviewer.data.movie

import com.android.test.movieviewer.data.movie.api.ApiDataMovieNowPlaying
import com.android.test.movieviewer.data.movie.api.ApiDataMoviesNowPlayingResponse
import com.android.test.movieviewer.data.movie.api.ApiTheMovieDb
import com.android.test.movieviewer.domain.util.Response
import com.android.test.movieviewer.ui.movie.util.Fake.Companion.EMOJI_MOVIE
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

    // Given API returns error, we return an error response
    @Test
    fun `given API throws error, then return error response`() = runBlockingTest {
        given(api.moviesNowPlaying()).willThrow(RuntimeException("Some issue"))

        val actual = movieRemoteRepository.getMovies()

        assertEquals(Response.Error, actual)
    }

    // Given API returns success, we return a success response
    @Test
    fun `given API returns result, then return success response`() = runBlockingTest {
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

}