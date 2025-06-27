package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.view.MovieList
import com.example.movieapp.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {

    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                Surface (color = MaterialTheme.colorScheme.background) {
                    movieViewModel.getMovieList()
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        MovieSearchBar()
                        Spacer(modifier = Modifier.height(2.dp))
                        MovieList(movieViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieSearchBar() {
    var text by remember { mutableStateOf(" ") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search Movies") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieSearchBar() {
    MovieAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MovieSearchBar()
        }
    }
}

