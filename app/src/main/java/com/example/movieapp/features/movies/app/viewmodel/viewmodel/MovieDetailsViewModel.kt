package com.example.movieapp.features.movies.app.viewmodel.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.platform.BasicViewModel
import com.example.movieapp.features.movies.app.viewmodel.model.MovieDetails
import com.example.movieapp.features.movies.domain.usecase.GetMovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetails: GetMovieDetails) :
    BasicViewModel() {
    var movieDetails by mutableStateOf<MovieDetails?>(null)
        private set

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetails.invoke(movieId, this) {
                it.fold(::handleFailure, ::handleMovieDetails)
            }
        }
    }

    fun handleMovieDetails(movieDetails: MovieDetails) {
        this.movieDetails = movieDetails
    }
}