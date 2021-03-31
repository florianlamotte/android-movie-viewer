package com.android.test.movieviewer.ui.movie.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.movie.usecase.GetMovies
import com.android.test.movieviewer.domain.util.Response
import com.android.test.movieviewer.ui.movie.util.Fake.Companion.EMOJI_MOVIE
import com.android.test.movieviewer.ui.movie.util.TestCoroutineContextProvider
import com.android.test.movieviewer.ui.util.UIState
import com.nhaarman.mockitokotlin2.given
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesListViewModelTest : TestCase() {

    @Rule
    @JvmField
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val coroutineContextProvider = TestCoroutineContextProvider()
    private val dispatcher = coroutineContextProvider.testCoroutineDispatcher

    private lateinit var viewModel: MoviesListViewModel

    @Mock
    private lateinit var getMovies: GetMovies

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MoviesListViewModel(
            coroutineContextProvider,
            getMovies
        )
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    // Test invalid data / error received
    @Test
    fun `given invalid model data, then return loading and error view state`() = runBlockingTest {
        given(getMovies()).willReturn(Response.Error)

        dispatcher.pauseDispatcher()
        viewModel.refreshMovies()

        assertEquals(UIState.Loading, viewModel.state.value)
        dispatcher.resumeDispatcher()
        assertEquals(UIState.Error, viewModel.state.value)
    }

    // Test loading then success view state
    @Test
    fun `given valid model data, then return loading and success view state`() = runBlockingTest {
        given(getMovies()).willReturn(Response.Success(emptyList()))

        dispatcher.pauseDispatcher()
        viewModel.refreshMovies()

        assertEquals(UIState.Loading, viewModel.state.value)
        dispatcher.resumeDispatcher()
        assertEquals(UIState.Success<List<MoviesListItem>>(emptyList()), viewModel.state.value)
    }

    // Test valid data received in success view state
    @Test
    fun `given valid model data, then return valid movies items`() = runBlockingTest {
        given(getMovies()).willReturn(Response.Success(listOf(
            EMOJI_MOVIE
        )))

        viewModel.refreshMovies()

        val actual = viewModel.state.value as UIState.Success
        assertEquals(listOf(
            MoviesListItem(
                MovieId("emoji"),
                "The Emoji Movie")
        ),
            actual.data
        )
    }

}