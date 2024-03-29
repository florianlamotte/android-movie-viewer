package com.android.test.movieviewer.domain.movie.usecase

import com.android.test.movieviewer.domain.movie.MovieRepository
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
class GetMoviesTest {

    private lateinit var getMovies: GetMovies

    @Mock
    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        getMovies = GetMovies(repository)
    }

    // Given repository responds error, then return error
    @Test
    fun `given repository responds error, then return error`() = runBlockingTest {
        given(repository.getMovies()).willReturn(Response.Error)

        val actual = getMovies()

        assertEquals(Response.Error, actual)
    }

    // Given repository responds success, then return success
    @Test
    fun `given repository responds success, then return success`() = runBlockingTest {
        given(repository.getMovies()).willReturn(Response.Success(listOf(EMOJI_MOVIE)))

        val actual = getMovies()

        assertEquals(Response.Success(listOf(EMOJI_MOVIE)), actual)
    }
}