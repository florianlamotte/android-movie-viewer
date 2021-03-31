package com.android.test.movieviewer.data.movie.di

import com.android.test.movieviewer.data.movie.api.ApiTheMovieDb
import com.android.test.movieviewer.data.movie.api.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class DataModule {

    companion object {

        @Provides
        fun provideTheMovieDbApi() : ApiTheMovieDb {
            return RetrofitApi.retrofit.create(ApiTheMovieDb::class.java)
        }

    }

}