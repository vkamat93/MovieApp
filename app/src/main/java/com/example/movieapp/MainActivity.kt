package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import com.example.movieapp.model.Description
import com.example.movieapp.model.SearchResults
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.view.MovieItem
import com.example.movieapp.view.MovieList
import com.example.movieapp.viewmodel.MovieViewModel
import kotlinx.serialization.SerialName

class MainActivity : ComponentActivity() {

    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                Surface (color = MaterialTheme.colorScheme.background) {
                    movieViewModel.getMovieList()
                    MovieList(movieViewModel)
                }
            }
        }
    }
}