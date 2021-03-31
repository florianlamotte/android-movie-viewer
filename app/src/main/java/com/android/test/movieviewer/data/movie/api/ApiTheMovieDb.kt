package com.android.test.movieviewer.data.movie.api

import com.android.test.movieviewer.data.movie.api.RetrofitApi.API_KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://api.themoviedb.org/3/"

interface ApiTheMovieDb {

    @Throws(RuntimeException::class)
    @GET("movie/now_playing?api_key=${API_KEY}")
    suspend fun moviesNowPlaying(): ApiDataMoviesNowPlayingResponse

    @Throws(RuntimeException::class)
    @GET("movie/{movieId}?api_key=${API_KEY}")
    suspend fun movie(@Path("movieId") movieId: String): ApiDataMovieResponse

}

object RetrofitApi {

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    const val API_KEY = "6dd74c81b2344841555a0762da19589c"

}
