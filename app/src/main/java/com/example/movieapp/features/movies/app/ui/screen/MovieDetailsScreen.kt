package com.example.movieapp.features.movies.app.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.features.movies.app.viewmodel.model.MovieDetails
import com.example.movieapp.features.movies.app.viewmodel.viewmodel.MovieDetailsViewModel
import kotlin.math.min

@Composable
fun MovieDetailsScreen(movieId: Int, viewModel: MovieDetailsViewModel, modifier: Modifier) {
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        viewModel.getMovieDetails(movieId)
    }
    Column {
        /* Text(
             modifier = Modifier.padding(15.dp),
             text = viewModel.movieDetails?.title ?: "",
             fontSize = 25.sp,
             fontWeight = FontWeight.ExtraBold,
             letterSpacing = 1.sp
         )
         Spacer(modifier = Modifier.height(15.dp))*/
        Column(/*modifier = Modifier.verticalScroll(scrollState)*/) {
            Poster(modifier = Modifier, poster = viewModel.movieDetails?.poster ?: "") { }
            Spacer(modifier = Modifier.height(15.dp))
            Details(modifier = Modifier, movieDetails = viewModel.movieDetails)
            Spacer(modifier = Modifier.height(10.dp))
            //MovieDetails(modifier = Modifier, movieDetails = viewModel.movieDetails)
        }
    }
}

@Composable
fun Poster(modifier: Modifier, poster: String, onPlayClicked: () -> Unit) {
    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(.4f)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            model = poster
        )
        IconButton(
            onClick = { onPlayClicked() },
            modifier = Modifier
                .align(Alignment.Center)
                .size(70.dp)
        ) {
            Image(painter = painterResource(R.drawable.play), contentDescription = "")
        }
    }
}


@Composable
fun Details(modifier: Modifier, movieDetails: MovieDetails?) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .padding(horizontal = 15.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = movieDetails?.title ?: "",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row {
            TextIcon(
                modifier = modifier,
                icon = ImageVector.vectorResource(R.drawable.time),
                text = "${movieDetails?.runtime.toString()} Minutes"
            )

            Spacer(modifier = Modifier.width(20.dp))

            TextIcon(
                modifier = modifier,
                icon = Icons.Default.Star,
                text = "${movieDetails?.roundedRating.toString()} (IMDB)"
            )
        }

        Splitter()

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(text = "Release date")
                Spacer(modifier = Modifier.height(7.5.dp))
                Text(text = movieDetails?.releaseDate ?: "")
            }

            Column {
                Text(text = "Genre")
                Spacer(modifier = Modifier.height(5.dp))
                LazyRow {
                    items(
                        movieDetails?.genres?.subList(0, min(2, movieDetails.genres.size))
                            ?: emptyList()
                    ) {
                        Card(border = BorderStroke(0.5.dp, Color.LightGray)) {
                            Text(
                                text = it.name,
                                modifier = Modifier.padding(4.dp),
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        }

        Splitter()
        //Log.d(TAG, "Details: summary:  ${movieDetails?.summary}")

        Synopsis(modifier = modifier, summary = movieDetails?.summary)
    }
}

@Composable
fun TextIcon(modifier: Modifier, icon: ImageVector, text: String) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(7.dp))
        Text(text = text, fontWeight = FontWeight.Thin)
    }
}

@Composable
fun Splitter() {
    Spacer(modifier = Modifier.height(15.dp))
    Spacer(
        modifier = Modifier
            .height(0.3.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(Color.Gray)
    )
    Spacer(modifier = Modifier.height(15.dp))
}

private const val TAG = "MovieDetailsScreen"

@Composable
fun Synopsis(modifier: Modifier, summary: String?) {
    val isClickable by remember(summary) { mutableStateOf(if (summary.isNullOrEmpty()) false else summary.length > 300) }
    var clicked by remember { mutableStateOf(false) }
    val synopsis by remember(summary, clicked) {
        mutableStateOf(
            if (clicked) summary
            else
                summary?.substring(
                    0,
                    minOf(summary.length, 300)
                ) + if (isClickable) " Read More..." else ""
        )
    }

    Column(modifier) {
        Text(text = "Synopsis")
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = synopsis ?: "",
            modifier = Modifier.clickable {
                clicked = !clicked
            })
    }
}


