package com.example.movieapp.features.movies.app.viewmodel.model

import com.example.movieapp.features.movies.domain.entity.Genre
import kotlin.math.ceil

data class MovieDetails(
    val id: Int,
    val title: String,
    val poster: String,
    val summary: String,
    val runtime: Int,
    val releaseDate: String,
    val rating: Double,
    val genres: List<Genre>
) {
    val roundedRating: Double
        get() = ceil(rating)
}