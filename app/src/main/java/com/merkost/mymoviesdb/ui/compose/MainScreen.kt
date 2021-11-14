package com.merkost.mymoviesdb.ui.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.merkost.mymoviesdb.MainActivity
import com.merkost.mymoviesdb.model.entity.Top250DataDetail
import com.merkost.mymoviesdb.viewmodels.MainViewModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import org.koin.androidx.compose.get

@Composable
@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
fun MainScreen(navController: NavController) {
    val viewModel: MainViewModel = get()
    // A surface container using the 'background' color from the theme
    /*Surface(color = MaterialTheme.colors.background) {
        Greeting("Android")
    }*/
    val moviesList = viewModel.currentContent.collectAsState()


    if (moviesList.value.isNullOrEmpty()) {
        EmptyView()
    } else {
        LazyVerticalGrid(cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),) {

            items(moviesList.value) {
                MainMovieItem(it) {
                    navController.navigate("${MainActivity.MainDestinations.MOVIE_ROUTE}/${it.id}")
                }
            }
        }
    }
}

@Composable
fun EmptyView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,) {
            Text("Loading movies")
            Spacer(modifier = Modifier.size(8.dp))
            CircularProgressIndicator()
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MainMovieItem(movie: Top250DataDetail, onMovieClicked: () -> Unit) {
    Card(modifier = Modifier.requiredHeight(290.dp).padding(4.dp),
        shape = RoundedCornerShape(8.dp), onClick = onMovieClicked) {
        CoilImage(
            imageModel = movie.image,
            contentScale = ContentScale.Crop,
            shimmerParams = ShimmerParams(
                baseColor = Color.LightGray,
                highlightColor = Color.White,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f,
            ),
            failure = {
                Text("Image request failed")
            }
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}