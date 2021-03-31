package com.android.test.movieviewer.ui.util

sealed class UIState {

    object Loading: UIState()

    object Error: UIState()

    object Success: UIState()

}