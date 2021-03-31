package com.android.test.movieviewer.data.movie.di

import com.android.test.movieviewer.data.movie.MovieRemoteRepository
import com.android.test.movieviewer.data.movie.api.ApiTheMovieDb
import com.android.test.movieviewer.data.movie.api.RetrofitApi
import com.android.test.movieviewer.domain.movie.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideMovieRepository(movieRemoteRepository: MovieRemoteRepository): MovieRepository

    companion object {

        @Provides
        fun provideTheMovieDbApi() : ApiTheMovieDb {
            return RetrofitApi.retrofit.create(ApiTheMovieDb::class.java)
        }

    }

}