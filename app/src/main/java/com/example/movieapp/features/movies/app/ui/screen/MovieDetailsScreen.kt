package com.example.movieapp.features.movies.app.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.movieapp.features.movies.app.viewmodel.model.MovieDetails
import com.example.movieapp.features.movies.app.viewmodel.viewmodel.MovieDetailsViewModel
import kotlin.math.ceil

@Composable
fun MovieDetailsScreen(movieId: Int, viewModel: MovieDetailsViewModel, modifier: Modifier) {
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        viewModel.getMovieDetails(movieId)
    }
    Column {
        Text(
            modifier = Modifier.padding(15.dp),
            text = viewModel.movieDetails?.title ?: "",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        Column(/*modifier = Modifier.verticalScroll(scrollState)*/) {
            Banner(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.4f), poster = viewModel.movieDetails?.poster
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieDetails(modifier = Modifier, movieDetails = viewModel.movieDetails)
        }
    }
}

@Composable
fun Banner(modifier: Modifier, poster: String?) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            model = poster
        )
        YoutubeButton(modifier = Modifier.align(Alignment.BottomEnd)) { }
    }
}

@Composable
fun YoutubeButton(modifier: Modifier, onClick: () -> Unit) {
    Row(modifier = modifier) {
        IconButton(colors = IconButtonDefaults.iconButtonColors().copy(containerColor = Color.Red),
            modifier = Modifier.size(46.dp),
            onClick = {
                onClick()
            }) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "",
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
}

@Composable
fun MovieDetails(modifier: Modifier, movieDetails: MovieDetails?) {
    Text(
        text = "Summary:",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        textDecoration = TextDecoration.Underline
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = movieDetails?.summary ?: "",
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    StarRatingBar(rating = ceil(movieDetails?.rating?.toFloat() ?: (1f / 10f))) { }
}

@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    val density = LocalDensity.current.density
    val starSize = (12f * density).dp
    val starSpacing = (0.5f * density).dp

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color(0x20FFFFFF)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            //onRatingChanged(i.toFloat())
                        }
                    )
                    .width(starSize)
                    .height(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}