package com.android.test.movieviewer.ui.movie.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.test.movieviewer.ui.util.UIState
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest {

    private lateinit var viewModel: MovieDetailsViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = MovieDetailsViewModel()
    }

    // Test when get movie returns error, then state loading error
    @Test
    fun `given invalid model data, then return loading and error view state`() {
        viewModel.getMovieDetails()

        assertEquals(UIState.Error, viewModel.uiState.value)
    }

}