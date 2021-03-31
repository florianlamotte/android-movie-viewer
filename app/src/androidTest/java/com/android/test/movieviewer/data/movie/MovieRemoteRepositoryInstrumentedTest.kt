package com.android.test.movieviewer.data.movie

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.test.movieviewer.data.movie.di.DataModule
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.util.Response
import kotlinx.coroutines.runBlocking

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieRemoteRepositoryInstrumentedTest {

    private lateinit var repository: MovieRemoteRepository

    @Before
    fun setup() {
        repository = MovieRemoteRepository(DataModule.provideTheMovieDbApi())
    }

    @Test
    fun testNowPlayingResponse() = runBlocking {
        val actual = repository.getMovies()
        assertTrue(actual is Response.Success)
        with(actual as Response.Success) {
            assertTrue(data.isNotEmpty())
            assertTrue(data[0].title.isNotBlank())
        }
    }

    @Test
    fun testMovieResponse() = runBlocking {
        val actual = repository.getMovieById(MovieId("399566"))
        assertTrue(actual is Response.Success)
        with(actual as Response.Success) {
            assertTrue(data.title == "Godzilla vs. Kong")
        }
    }

}