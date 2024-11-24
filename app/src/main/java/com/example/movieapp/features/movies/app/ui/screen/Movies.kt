package com.example.movieapp.features.movies.app.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.movieapp.features.movies.app.viewmodel.model.Movie

@Composable
fun MoviesScreen(movies: List<Movie>, modifier: Modifier = Modifier, onScrollFinished: () -> Unit) {
    LazyVerticalGrid(
        GridCells.Fixed(3),
        modifier = modifier
    ) {
        items(movies.size) { index ->
            MovieField(movie = movies[index], modifier = Modifier.padding(2.dp)) {

            }
            if (index == movies.size - 1) onScrollFinished()
        }
    }
}

@Composable
fun MovieField(movie: Movie, modifier: Modifier, onClick: () -> Unit) {
    Box(modifier.clickable { }) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie.poster}",
            contentDescription = null,
        )
        Text(text = movie.name, modifier = Modifier.align(Alignment.BottomCenter))
    }
}