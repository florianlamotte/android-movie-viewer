package com.android.test.movieviewer.ui.movie.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.movie.usecase.GetMovies
import com.android.test.movieviewer.domain.util.Response
import com.android.test.movieviewer.ui.util.UIState
import com.nhaarman.mockitokotlin2.given
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
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

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MoviesListViewModel

    @Mock
    private lateinit var getMovies: GetMovies

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = MoviesListViewModel(
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
        given(getMovies.execute()).willReturn(Response.Error)

        testCoroutineDispatcher.pauseDispatcher()
        viewModel.getMovies()

        assertEquals(UIState.Loading, viewModel.state.value)
        testCoroutineDispatcher.resumeDispatcher()
        assertEquals(UIState.Error, viewModel.state.value)
    }

    // Test loading then success view state
    @Test
    fun `given valid model data, then return loading and success view state`() = runBlockingTest {
        given(getMovies.execute()).willReturn(Response.Success(emptyList()))

        testCoroutineDispatcher.pauseDispatcher()
        viewModel.getMovies()

        assertEquals(UIState.Loading, viewModel.state.value)
        testCoroutineDispatcher.resumeDispatcher()
        assertEquals(UIState.Success<List<MoviesListItem>>(emptyList()), viewModel.state.value)
    }

    // Test valid data received in success view state
    @Test
    fun `given valid model data, then return valid movies items`() = runBlockingTest {
        given(getMovies.execute()).willReturn(Response.Success(listOf(
            Movie("The Emoji Movie")
        )))

        viewModel.getMovies()

        val actual = viewModel.state.value as UIState.Success
        assertEquals(listOf(
            MoviesListItem("The Emoji Movie")),
            actual.data
        )
    }

}