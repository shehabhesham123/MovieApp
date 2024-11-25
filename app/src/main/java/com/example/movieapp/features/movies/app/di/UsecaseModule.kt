package com.example.movieapp.features.movies.app.di

import com.example.movieapp.features.movies.domain.repo.MoviesRepo
import com.example.movieapp.features.movies.domain.usecase.GetMovieDetails
import com.example.movieapp.features.movies.domain.usecase.GetMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {
    @Provides
    fun provideGetMovies(moviesRepo: MoviesRepo): GetMovies {
        return GetMovies(moviesRepo)
    }

    @Provides
    fun provideGetMovieDetails(moviesRepo: MoviesRepo): GetMovieDetails {
        return GetMovieDetails(moviesRepo)
    }
}