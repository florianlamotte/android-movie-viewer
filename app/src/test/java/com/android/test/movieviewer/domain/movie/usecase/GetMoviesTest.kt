package com.android.test.movieviewer.domain.movie.usecase

import com.android.test.movieviewer.domain.movie.MovieRepository
import com.android.test.movieviewer.domain.util.Response
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMoviesTest {

    private lateinit var getMovies: GetMovies

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        getMovies = GetMovies(movieRepository)
    }

    // Given repository responds error, then return error
    @Test
    fun `given repository responds error, then return error`() = runBlockingTest {
        given(movieRepository.getMovies()).willReturn(Response.Error)

        val actual = getMovies.execute()

        assertEquals(Response.Error, actual)
    }

    // Given repository responds success, then return success
}