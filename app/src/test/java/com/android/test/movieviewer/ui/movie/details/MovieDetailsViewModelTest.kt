package com.android.test.movieviewer.ui.movie.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.movie.usecase.GetMovieById
import com.android.test.movieviewer.domain.util.Response
import com.android.test.movieviewer.ui.movie.util.Fake.Companion.EMOJI_MOVIE_DETAILS
import com.android.test.movieviewer.ui.movie.util.Fake.Companion.EMOJI_MOVIE_DETAILS_NO_COLLECTION
import com.android.test.movieviewer.ui.movie.util.TestCoroutineContextProvider
import com.android.test.movieviewer.ui.util.UIState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest {

    private lateinit var viewModel: MovieDetailsViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineContextProvider = TestCoroutineContextProvider()
    private val dispatcher = coroutineContextProvider.testCoroutineDispatcher

    @Mock
    private lateinit var getMovieById: GetMovieById

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    // Test when get movie returns error, then state loading / error
    @Test
    fun `given invalid model data, then return loading and error view state`() = runBlockingTest {
        given(getMovieById(any())).willReturn(Response.Error)

        dispatcher.pauseDispatcher()
        `init view model`()

        assertEquals(UIState.Loading, viewModel.uiState.value)
        dispatcher.resumeDispatcher()
        assertEquals(UIState.Error, viewModel.uiState.value)
    }

    // Test when get movie returns success, then state loading / success
    @Test
    fun `given valid model data, then return loading and success view state`() = runBlockingTest {
        given(getMovieById(any()))
            .willReturn(Response.Success(EMOJI_MOVIE_DETAILS))
        dispatcher.pauseDispatcher()
        `init view model`()

        assertEquals(UIState.Loading, viewModel.uiState.value)
        dispatcher.resumeDispatcher()
        assertEquals(UIState.Success(
            MovieDetailsItem(
                "The Emoji Movie",
                "The Emos",
                listOf(
                    CollectionItem(MovieId("emoji"), "The Emoji Movie", "https://image.tmdb.org/t/p/original/emoji-url"),
                    CollectionItem(MovieId("emoji2"), "The Emoji Movie II", "https://image.tmdb.org/t/p/original/emoji-url2"),
                )
            )), viewModel.uiState.value)
    }

    // Test when get movie returns success, then state loading / success
    @Test
    fun `given valid model data no collection, then return loading and success view state`() = runBlockingTest {
        given(getMovieById(any()))
            .willReturn(Response.Success(EMOJI_MOVIE_DETAILS_NO_COLLECTION))
        dispatcher.pauseDispatcher()
        `init view model`()

        assertEquals(UIState.Loading, viewModel.uiState.value)
        dispatcher.resumeDispatcher()
        assertEquals(UIState.Success(
            MovieDetailsItem(
                "The Emoji Movie",
                null,
                null
            )), viewModel.uiState.value)
    }

    private fun `init view model`() {
        viewModel = MovieDetailsViewModel(coroutineContextProvider, getMovieById, MovieId("emoji"))
    }

}