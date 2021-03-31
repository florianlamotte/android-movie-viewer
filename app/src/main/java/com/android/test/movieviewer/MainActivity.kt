package com.android.test.movieviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.test.movieviewer.ui.movie.list.MoviesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviesListFragment.newInstance())
                .commitNow()
        }
    }
}