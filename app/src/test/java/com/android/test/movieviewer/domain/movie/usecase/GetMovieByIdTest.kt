package com.android.test.movieviewer.domain.movie.usecase

import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.movie.MovieRepository
import com.android.test.movieviewer.domain.util.Response
import com.android.test.movieviewer.ui.movie.util.Fake.Companion.EMOJI_MOVIE
import com.android.test.movieviewer.ui.movie.util.Fake.Companion.EMOJI_MOVIE_DETAILS
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieByIdTest {

    private lateinit var getMovieById: GetMovieById

    @Mock
    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        getMovieById = GetMovieById(repository)
    }

    // Given repository responds error, then return error
    @Test
    fun `given repository responds error, then return error`() = runBlockingTest {
        given(repository.getMovieById(any())).willReturn(Response.Error)

        val actual = getMovieById(MovieId("some id"))

        assertEquals(Response.Error, actual)
    }

    // Given repository responds success, then return success
    @Test
    fun `given repository responds success, then return success`() = runBlockingTest {
        given(repository.getMovieById(any())).willReturn(Response.Success(EMOJI_MOVIE_DETAILS))

        val actual = getMovieById(MovieId("some id"))

        assertEquals(Response.Success(EMOJI_MOVIE_DETAILS), actual)
    }
}