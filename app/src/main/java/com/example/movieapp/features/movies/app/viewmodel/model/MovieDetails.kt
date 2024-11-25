package com.example.movieapp.features.movies.app.viewmodel.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val poster: String,
    val summary: String,
    val releaseDate: String,
    val rating: Double
) {
}