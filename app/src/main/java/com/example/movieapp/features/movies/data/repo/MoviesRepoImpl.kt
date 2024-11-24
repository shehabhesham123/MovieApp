package com.example.movieapp.features.movies.data.repo

import com.example.movieapp.core.failure.Failure
import com.example.movieapp.core.funcational.Either
import com.example.movieapp.features.movies.app.viewmodel.model.Movie
import com.example.movieapp.features.movies.data.remote.ApiService
import com.example.movieapp.features.movies.domain.entity.MoviesResponse
import com.example.movieapp.features.movies.domain.repo.MoviesRepo
import retrofit2.Call


class MoviesRepoImpl(private val apiService: ApiService) : MoviesRepo() {
    override suspend fun getMovies(pageNumber: Int): Either<Failure, List<Movie>> {
        // get Movies From the network
        return request(
            apiService.getMovies(page = pageNumber),
            { response ->
                response.results.map { it.toMovie() }
            }, MoviesResponse.empty()
        )
    }

    private fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}