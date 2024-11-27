package com.example.movieapp.features.movies.domain.entity

import com.example.movieapp.features.movies.app.viewmodel.model.MovieDetails

data class MovieDetailsEntity(
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    val imdb_id: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val runtime: Int,
    val vote_average: Double,
    val vote_count: Int
) {

    fun toMovieDetails(): MovieDetails {
        return MovieDetails(
            id,
            title,
            "https://image.tmdb.org/t/p/w500/${poster_path}",
            overview,
            runtime,
            release_date,
            vote_average,
            genres
        )
    }

    companion object {
        fun empty() = MovieDetailsEntity(0, emptyList(), 0, "", "", 0.0, "", "", "", 0, 0.0, 0)
    }
}