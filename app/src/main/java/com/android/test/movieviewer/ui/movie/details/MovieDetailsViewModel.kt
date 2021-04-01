package com.android.test.movieviewer.ui.movie.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.movie.usecase.GetMovieById
import com.android.test.movieviewer.domain.util.Response
import com.android.test.movieviewer.ui.util.CoroutineContextProvider
import com.android.test.movieviewer.ui.util.UIState
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val getMovieById: GetMovieById,
    private val movieId: MovieId
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState<MovieDetailsItem>>()
    val uiState: LiveData<UIState<MovieDetailsItem>> get() = _uiState

    fun getMovieDetails() {
        _uiState.postValue(UIState.Loading)
        viewModelScope.launch(coroutineContextProvider.IO) {
            val state = when (val response = getMovieById(movieId)) {
                is Response.Error -> UIState.Error
                is Response.Success -> {
                    val item = with(response.data) {
                        MovieDetailsItem(
                            title,
                            collection?.collectionName,
                            collection?.movieCollection?.map { collectionItem ->
                                CollectionItem(
                                    collectionItem.id,
                                    collectionItem.title,
                                    collectionItem.imageUrl
                                )
                            }
                        )
                    }
                    UIState.Success(item)
                }
            }
            _uiState.postValue(state)
        }
    }

}