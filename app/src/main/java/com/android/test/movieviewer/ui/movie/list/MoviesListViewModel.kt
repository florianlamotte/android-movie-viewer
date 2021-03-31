package com.android.test.movieviewer.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.test.movieviewer.ui.util.UIState

class MoviesListViewModel : ViewModel() {

    private val _state = MutableLiveData<UIState>()
    val state: LiveData<UIState>
        get() = _state

    fun getMovies() {
        _state.postValue(UIState.Error)
    }

}