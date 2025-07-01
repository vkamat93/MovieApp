package com.example.movieapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieapp.R
import com.example.movieapp.model.DescriptionJW
import com.example.movieapp.model.Offer
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.MovieViewModel

@Composable
fun MovieList(movieViewModel: MovieViewModel) {
    // This composable function displays a list of movies using LazyColumn.
    val movieList = movieViewModel.movieListResponse.observeAsState()
    val isLoading = movieViewModel.isLoading.observeAsState()
    val firstLaunch = movieViewModel.firstLaunch.observeAsState()

    if (firstLaunch.value == true) {
        // Initial welcome message or instructions can be displayed here.
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Welcome to the Movie App! \n" +
                        "Search for your favorite movies using the search bar above.",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }

    }

    if (isLoading.value == true) {
        // Show a loading indicator while the data is being fetched
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
    else {
        LazyColumn {
            itemsIndexed(items = movieList.value ?: emptyList()) { index, item ->
                MovieItem(description = item)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
//        val dummyDescription = DescriptionX(
//            actors = "Robert Downey Jr., Chris Evans, Mark Ruffalo",
//            aka = "",
//            imdbId = "tt4154796",
//            imdbIv = "",
//            imdbUrl = "",
//            imgPoster = "https://m.media-amazon.com/images/M/MV5BMGNiN2RlZTMtMTkyZC00YjkwLTgyY2QtMDg1ZDNhODQwNWM4XkEyXkFqcGc@._V1_.jpg",
//            rank = 23,
//            title = "Avengers: Endgame",
//            year = 2019,
//            photoWidth = 1382,
//            photoHeight = 2048
//        )
        val dummyMovie = DescriptionJW(
            backdrops = listOf(
                "https://image.tmdb.org/t/p/original/abcd1234_backdrop1.jpg",
                "https://image.tmdb.org/t/p/original/wxyz5678_backdrop2.jpg"
            ),
            id = "500",
            imdbId = "tt1234567",
            jwRating = 7.8,
            offers = listOf(
                Offer(name = "Netflix", type = "Movie", url = "https://netflix.com/watch/500"),
                Offer(name = "Prime Video", type = "Movie", url = "https://primevideo.com/title/500")
            ),
            photo_url = listOf(
                "https://image.tmdb.org/t/p/w500/photo1.jpg",
                "https://image.tmdb.org/t/p/w500/photo2.jpg"
            ),
            runtime = 125,
            title = "The Sample Movie",
            tmdbId = "500",
            tomatoCertifiedFresh = true,
            tomatoMeter = 85,
            type = "movie",
            url = "https://example.com/movies/500",
            year = 2023
        )
        MovieItem(description = dummyMovie)
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun MovieItem(description: DescriptionJW) {
    // This composable function displays a single movie item in the list.
    Card (
        modifier = Modifier.padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        border = BorderStroke(1.dp, Color.Cyan),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Surface () {
            Row (
                modifier = Modifier.padding(4.dp)
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(description.photo_url[0])
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.cinema),
                    contentDescription = description.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(5.dp)),
                )
                Column (
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = description.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Row ( ){
                        Text(
                            text = "Year: ${description.year}",
                            modifier = Modifier.background(Color.LightGray).padding(4.dp),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        val formatted = String.format("%.2f", description.jwRating * 100)
                        Text(
                            text = "JW Rating: $formatted%",
                            modifier = Modifier.background(Color.LightGray).padding(4.dp),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "Type: ${description.type}",
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}