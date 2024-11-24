package com.example.movieapp.core.failure

sealed class Failure {
    data object ConnectionError : Failure()
    data object ServerError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}