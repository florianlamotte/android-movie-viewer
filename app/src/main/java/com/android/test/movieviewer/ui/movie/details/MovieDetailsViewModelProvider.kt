package com.android.test.movieviewer.ui.movie.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.movie.usecase.GetMovieById
import com.android.test.movieviewer.ui.util.CoroutineContextProvider
import javax.inject.Inject

class MovieDetailsViewModelProvider @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val getMovieById: GetMovieById,
    private val movieId: MovieId
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(coroutineContextProvider, getMovieById, movieId) as T
    }
}