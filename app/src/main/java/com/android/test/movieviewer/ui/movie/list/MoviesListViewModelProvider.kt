package com.android.test.movieviewer.ui.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.test.movieviewer.domain.movie.usecase.GetMovies
import javax.inject.Inject

class MoviesListViewModelProvider @Inject constructor(
    private val getMovies: GetMovies
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesListViewModel(
            getMovies
        ) as T
    }
}