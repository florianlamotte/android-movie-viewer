package com.android.test.movieviewer.domain.util

sealed class Response {

    object Error: Response()

    class Success: Response()

}