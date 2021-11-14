package com.merkost.mymoviesdb.ui.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merkost.mymoviesdb.viewmodels.MainViewModel
import com.merkost.mymoviesdb.viewmodels.MovieDetailsViewModel
import org.koin.androidx.compose.get

@Composable
@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
fun MovieDetailsScreen(navController: NavController, movieId: String?) {
    val viewModel: MovieDetailsViewModel = get()
    val selectedMovie = viewModel.currentContent.collectAsState()


    if (moviesList.value.isNullOrEmpty()) {
        Surface(color = MaterialTheme.colors.background) {
            Greeting("Android")
        }
    } else {

        LazyVerticalGrid(cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),) {

            items(moviesList.value) {
                MainMovieItem(it) {

                }
            }
        }
    }
}