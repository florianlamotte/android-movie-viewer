package com.android.test.movieviewer.ui.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.test.movieviewer.domain.movie.usecase.GetMovies
import com.android.test.movieviewer.ui.util.CoroutineContextProvider
import javax.inject.Inject

class MoviesListViewModelProvider @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val getMovies: GetMovies
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesListViewModel(
            coroutineContextProvider,
            getMovies
        ) as T
    }
}