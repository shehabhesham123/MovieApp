package com.example.movieapp.features.movies.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.features.movies.app.viewmodel.viewmodel.MovieDetailsViewModel
import com.example.movieapp.features.movies.app.viewmodel.viewmodel.MoviesViewModel

object Routes {
    const val MOVIES = "Movies"
    const val MOVIE_DETAILS = "Movie/{movieId}"
}

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MOVIES) {
        composable(Routes.MOVIES) {
            val moviesViewModel: MoviesViewModel = hiltViewModel()
            MoviesScreen(modifier = Modifier, viewModel = moviesViewModel, navController)
        }
        composable(
            route = Routes.MOVIE_DETAILS,
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            MovieDetailsScreen(movieId = movieId, viewModel = viewModel, modifier = Modifier)
        }
    }
}