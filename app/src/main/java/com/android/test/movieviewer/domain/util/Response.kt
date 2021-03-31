package com.android.test.movieviewer.domain.util

sealed class Response<out T : Any> {

    object Error: Response<Nothing>()

    data class Success<out T : Any>(
        val data: T
    ) : Response<T>()
}