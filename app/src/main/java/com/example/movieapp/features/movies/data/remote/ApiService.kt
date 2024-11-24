package com.example.movieapp.features.movies.data.remote

import com.example.movieapp.features.movies.domain.entity.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Call<MoviesResponse>
}