package com.example.movieapp.features.movies.domain.usecase

import com.example.movieapp.core.failure.Failure
import com.example.movieapp.core.funcational.Either
import com.example.movieapp.core.usecase.Usecase
import com.example.movieapp.features.movies.app.viewmodel.model.MovieDetails
import com.example.movieapp.features.movies.domain.repo.MoviesRepo

class GetMovieDetails(private val moviesRepo: MoviesRepo) : Usecase<Int, MovieDetails>() {
    override suspend fun run(param: Int): Either<Failure, MovieDetails> {
        return moviesRepo.getMovieDetails(param)
    }
}