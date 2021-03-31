package com.android.test.movieviewer.ui.movie.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.test.movieviewer.ui.util.UIState
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesListViewModelTest : TestCase() {

    @Rule
    @JvmField
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesListViewModel

    @Before
    fun setup() {
        viewModel = MoviesListViewModel()
    }

    // Test invalid data / error received
    @Test
    fun `given invalid model data, then return error view state`() {
        viewModel.getMovies()
        assertEquals(UIState.Error, viewModel.state.value)
    }

    // Test valid data received

}