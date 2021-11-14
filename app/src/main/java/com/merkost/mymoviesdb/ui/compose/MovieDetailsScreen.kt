package com.merkost.mymoviesdb.ui.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merkost.mymoviesdb.R
import com.merkost.mymoviesdb.model.entity.ActorList
import com.merkost.mymoviesdb.model.entity.Movie
import com.merkost.mymoviesdb.viewmodels.MovieDetailsViewModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import org.koin.androidx.compose.get

@Composable
@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
fun MovieDetailsScreen(navController: NavController, movieId: String?) {
    val viewModel: MovieDetailsViewModel = get()
    viewModel.getMovieById(movieId)
    val selectedMovie = viewModel.currentContent.collectAsState()
    var movie = remember { mutableStateOf<Movie?>(null) }
    val scrollState = rememberScrollState()

    BottomSheetScaffold(
        backgroundColor = Color.LightGray,
        sheetContent = {

            movie.value?.let { notNullMovie ->
                Card(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(8.dp)) {
                    Column(

                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(12.dp).verticalScroll(scrollState)
                    ) {


                        Text(
                            notNullMovie.fullTitle,
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth().height(80.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,

                                ) {
                                Text(notNullMovie.imDbRating)
                                Icon(
                                    painterResource(R.drawable.imdb_icon), "",
                                    modifier = Modifier.size(45.dp)
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(notNullMovie.metacriticRating)
                                Icon(
                                    painterResource(R.drawable.metacritic_icon), "",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(Icons.Filled.AccessTime, "")
                                Text(notNullMovie.runtimeMins + " mins")
                            }

                        }


                        Text(notNullMovie.plot)
                        Text("Genres: ${notNullMovie.genres}")
                        LazyRow {
                            items(notNullMovie.actorList as List<ActorList>) {
                                ActorItem(it)
                            }
                        }
                        Text("Writers: ${notNullMovie.writers}")

                    }
                }
            }

        },
        sheetShape = RoundedCornerShape(12.dp),
        sheetPeekHeight = 470.dp,
    ) {
        if (selectedMovie.value == null) {
            Surface(color = MaterialTheme.colors.background) {
                Greeting("Android")
            }
        } else {
            movie.value = selectedMovie.value
            CoilImage(
                imageModel = movie.value!!.image,
                contentScale = ContentScale.FillHeight,
                shimmerParams = ShimmerParams(
                    baseColor = Color.LightGray,
                    highlightColor = Color.White,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f,
                ),
                failure = {
                    Text("Image request failed")
                },
                modifier = Modifier.requiredHeight(250.dp).wrapContentWidth().clip(CircleShape)
                    .padding(8.dp)
            )

        }
    }
}

@Composable
fun ActorItem(actorList: ActorList) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(2.dp).requiredWidth(120.dp)
    ) {
        Card {
            CoilImage(
                imageModel = actorList.image,
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
                },
                modifier = Modifier.requiredHeight(150.dp).requiredWidth(100.dp)
            )
        }
        Text(actorList.asCharacter, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
        Text(actorList.name, textAlign = TextAlign.Center)
    }
}
