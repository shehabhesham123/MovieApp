package com.example.movieapp.features.movies.domain.repo

import com.example.movieapp.core.failure.Failure
import com.example.movieapp.core.funcational.Either
import com.example.movieapp.features.movies.app.viewmodel.model.Movie
import com.example.movieapp.features.movies.app.viewmodel.model.MovieDetails

abstract class MoviesRepo {
    abstract suspend fun getMovies(pageNumber: Int): Either<Failure, List<Movie>>
    abstract suspend fun getMovieDetails(movieId: Int): Either<Failure, MovieDetails>
}