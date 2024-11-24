package com.example.movieapp.features.movies.domain.usecase

import com.example.movieapp.core.failure.Failure
import com.example.movieapp.core.funcational.Either
import com.example.movieapp.core.usecase.Usecase
import com.example.movieapp.features.movies.app.viewmodel.model.Movie
import com.example.movieapp.features.movies.domain.repo.MoviesRepo

class GetMovies(private val moviesRepo: MoviesRepo) : Usecase<Int, List<Movie>>() {
    override suspend fun run(param: Int): Either<Failure, List<Movie>> {
        return moviesRepo.getMovies(param)
    }
}
