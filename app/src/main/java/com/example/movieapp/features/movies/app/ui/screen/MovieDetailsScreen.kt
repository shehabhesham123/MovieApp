package com.example.movieapp.features.movies.app.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieapp.features.movies.app.viewmodel.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(movieId: Int?, viewModel: MovieDetailsViewModel, modifier: Modifier) {
    /*Column(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            contentDescription = null,
            model = movieDetails.poster
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = movieDetails.title)
    }*/
    Text(text = "Details")
}