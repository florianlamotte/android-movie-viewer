package com.android.test.movieviewer.data.movie

import com.android.test.movieviewer.data.movie.api.ApiTheMovieDb
import com.android.test.movieviewer.domain.util.Response
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

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
    fun `given API returns error, then return error response`() {
        val actual = movieRemoteRepository.getMovies()

        assertEquals(Response.Error, actual)
    }

    // Given API returns success, we return a success response

}