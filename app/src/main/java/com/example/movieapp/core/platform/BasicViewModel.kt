package com.example.movieapp.core.platform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.movieapp.core.failure.Failure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BasicViewModel : ViewModel() {

    var failure by mutableStateOf<Failure?>(null)
        private set

    fun handleFailure(failure: Failure) {
        this.failure = failure
    }
}