package com.android.test.movieviewer.ui.movie.util

import com.android.test.movieviewer.ui.util.CoroutineContextProvider
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

class TestCoroutineContextProvider: CoroutineContextProvider() {
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    override val Main: CoroutineContext = testCoroutineDispatcher
    override val IO: CoroutineContext = testCoroutineDispatcher
}