package com.example.movieapp.features.movies.app.di

import com.example.movieapp.features.movies.data.remote.ApiService
import com.example.movieapp.features.movies.data.repo.MoviesRepoImpl
import com.example.movieapp.features.movies.domain.repo.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideMoviesRepo(apiService: ApiService): MoviesRepo {
        return MoviesRepoImpl(apiService)
    }
}