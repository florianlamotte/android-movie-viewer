package com.android.test.movieviewer.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.movie.usecase.GetMovies
import com.android.test.movieviewer.domain.util.Response
import com.android.test.movieviewer.ui.util.UIState
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val getMovies: GetMovies
) : ViewModel() {

    private val _state = MutableLiveData<UIState<List<MoviesListItem>>>()
    val state: LiveData<UIState<List<MoviesListItem>>>
        get() = _state

    fun getMovies() {
        _state.postValue(UIState.Loading)
        viewModelScope.launch {
            val uiState = when (val response = getMovies.execute()) {
                is Response.Error -> UIState.Error
                is Response.Success<List<Movie>> -> {
                    UIState.Success<List<MoviesListItem>>(
                        response.data.map {
                            MoviesListItem(it.title)
                        }
                    )
                }
            }
            _state.postValue(uiState)
        }
    }

}