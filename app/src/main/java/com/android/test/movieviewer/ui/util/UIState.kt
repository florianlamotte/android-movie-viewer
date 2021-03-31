package com.android.test.movieviewer.ui.util

sealed class UIState<out T: Any> {

    object Loading: UIState<Nothing>()

    object Error: UIState<Nothing>()

    data class Success<out T: Any>(
        val data: T
    ): UIState<T>()

}