package com.android.test.movieviewer.data.movie

import com.android.test.movieviewer.data.movie.api.ApiTheMovieDb
import com.android.test.movieviewer.domain.movie.Movie
import com.android.test.movieviewer.domain.movie.MovieDetails
import com.android.test.movieviewer.domain.movie.MovieId
import com.android.test.movieviewer.domain.movie.MovieRepository
import com.android.test.movieviewer.domain.util.Response
import javax.inject.Inject

class MovieRemoteRepository @Inject constructor(
    private val theMovieDbAPi: ApiTheMovieDb
) : MovieRepository {

    override suspend fun getMovies(): Response<List<Movie>> {
        return try {
            val moviesNowPlaying = theMovieDbAPi.moviesNowPlaying().movies.map {
                Movie(
                    MovieId(it.id),
                    it.title
                )
            }
            Response.Success(moviesNowPlaying)
        } catch (e: Exception) {
            Response.Error
        }
    }

    override suspend fun getMovieById(id: MovieId): Response<MovieDetails> {
        return try {
            val apiMovie = theMovieDbAPi.movie(id.value)
            val collection = theMovieDbAPi.collection(apiMovie.collection.collectionId)
            Response.Success(
                apiMovie.let {
                    MovieDetails(
                        MovieId(it.id),
                        it.title,
                        it.collection.collectionName,
                        collection.parts.map {  collectionItem ->
                            Movie(
                                MovieId(collectionItem.movieId),
                                collectionItem.movieTitle
                            )
                        }
                    )
                })
        } catch (e: Exception) {
            Response.Error
        }
    }

}