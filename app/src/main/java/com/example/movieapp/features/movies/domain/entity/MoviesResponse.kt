package com.example.movieapp.features.movies.domain.entity

data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
) {
    companion object {
        fun empty() = MoviesResponse(Dates("0", "0"), 0, emptyList(), 0, 0)
    }
}