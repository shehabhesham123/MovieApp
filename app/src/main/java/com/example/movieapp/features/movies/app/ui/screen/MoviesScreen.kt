package com.example.movieapp.features.movies.app.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.movieapp.features.movies.app.viewmodel.model.Movie
import com.example.movieapp.features.movies.app.viewmodel.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }

    LazyVerticalGrid(
        GridCells.Fixed(3),
        modifier = modifier
    ) {
        items(viewModel.movies.size) { index ->
            MovieField(movie = viewModel.movies[index], modifier = Modifier.padding(2.dp)) {
                navController.navigate("Movie/${viewModel.movies[index].id}")
            }
            if (index == viewModel.movies.size - 1)
                viewModel.getMovies()
        }
    }
}

@Composable
fun MovieField(movie: Movie, modifier: Modifier, onClick: () -> Unit) {
    Box(modifier.clickable {
        onClick()
    }) {
        AsyncImage(
            model = movie.poster,
            contentDescription = null,
        )
        Text(text = movie.name, modifier = Modifier.align(Alignment.BottomCenter))
    }
}