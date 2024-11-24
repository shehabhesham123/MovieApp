package com.example.movieapp.features.movies.domain.entity

import com.example.movieapp.features.movies.app.viewmodel.model.Movie

data class MovieEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
){
    fun toMovie() = Movie(title, poster_path)
}