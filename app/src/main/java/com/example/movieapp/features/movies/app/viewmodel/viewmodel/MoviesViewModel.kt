package com.example.movieapp.features.movies.app.viewmodel.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.platform.BasicViewModel
import com.example.movieapp.features.movies.app.viewmodel.model.Movie
import com.example.movieapp.features.movies.domain.usecase.GetMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MoviesViewModel"

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMovies: GetMovies) : BasicViewModel() {

    var movies by mutableStateOf(emptyList<Movie>())
        private set
    private var currentPage = 1

    fun getMovies() {
        viewModelScope.launch {
            getMovies.invoke(currentPage++, viewModelScope) {
                it.fold(::handleFailure, ::handleMovies)
            }
        }
    }

    fun handleMovies(movies: List<Movie>) {
        Log.d(TAG, "handleMovies: $movies")
        this.movies += movies
    }
}