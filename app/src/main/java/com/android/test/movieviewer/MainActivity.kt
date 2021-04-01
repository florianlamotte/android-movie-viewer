package com.android.test.movieviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navController = this.findNavController(R.id.navigation_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navigation_host_fragment)
        return if (navController.currentDestination?.id == R.id.movieDetailsFragment) {
            navController.popBackStack(R.id.moviesListFragment, false)
            true
        } else {
            navController.navigateUp()
        }
    }
}