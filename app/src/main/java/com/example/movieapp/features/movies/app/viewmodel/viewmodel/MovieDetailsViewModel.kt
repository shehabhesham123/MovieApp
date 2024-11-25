package com.example.movieapp.features.movies.app.viewmodel.viewmodel

import com.example.movieapp.core.platform.BasicViewModel
import com.example.movieapp.features.movies.domain.usecase.GetMovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetails: GetMovieDetails) :
    BasicViewModel() {
}