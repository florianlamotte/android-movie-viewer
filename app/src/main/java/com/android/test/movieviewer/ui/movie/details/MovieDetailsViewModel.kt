package com.android.test.movieviewer.ui.movie.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.test.movieviewer.ui.util.UIState

class MovieDetailsViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UIState<MovieDetailsItem>>()
    val uiState: LiveData<UIState<MovieDetailsItem>> get() = _uiState

    fun getMovieDetails() {
        _uiState.postValue(UIState.Error)
    }

}