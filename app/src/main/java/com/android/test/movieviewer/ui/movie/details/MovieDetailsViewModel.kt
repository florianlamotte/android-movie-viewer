package com.android.test.movieviewer.ui.movie.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.test.movieviewer.domain.movie.usecase.GetMovieById
import com.android.test.movieviewer.ui.util.CoroutineContextProvider
import com.android.test.movieviewer.ui.util.UIState
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val getMovieById: GetMovieById
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState<MovieDetailsItem>>()
    val uiState: LiveData<UIState<MovieDetailsItem>> get() = _uiState

    fun getMovieDetails() {
        _uiState.postValue(UIState.Loading)
        viewModelScope.launch(coroutineContextProvider.IO) {
            _uiState.postValue(UIState.Error)
        }
    }

}