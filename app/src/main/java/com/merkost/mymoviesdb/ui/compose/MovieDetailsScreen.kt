package com.merkost.mymoviesdb.ui.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merkost.mymoviesdb.MainActivity
import com.merkost.mymoviesdb.R
import com.merkost.mymoviesdb.model.entity.ActorList
import com.merkost.mymoviesdb.model.entity.Movie
import com.merkost.mymoviesdb.model.entity.Similars
import com.merkost.mymoviesdb.model.entity.Top250DataDetail
import com.merkost.mymoviesdb.resources
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
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState =
        BottomSheetState(BottomSheetValue.Collapsed))
    val scrollState = rememberScrollState()
    //val activity = LocalContext.current as MainActivity
    val bottomSheetPeekHeight = remember { mutableStateOf(0.dp)}
    val (height, width) = resources().displayMetrics.run { heightPixels/density to widthPixels/density }
    val preparedBottomSheetPeekHeight = ((3 * height / 5).toInt() -12).dp

    BottomSheetScaffold(
        backgroundColor = Color.LightGray,
        scaffoldState = scaffoldState,
        sheetPeekHeight = bottomSheetPeekHeight.value,
        sheetBackgroundColor = Color.LightGray,
        sheetContent = {
            Spacer(Modifier.size(1.dp))
            movie.value?.let { notNullMovie ->
                Column(

                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    Card(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(16.dp)) {
                        Column(

                            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(12.dp)
                        ) {


                            Text(
                                notNullMovie.fullTitle ?: notNullMovie.title,
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
                                        modifier = Modifier.size(60.dp)
                                    )
                                }
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(notNullMovie.metacriticRating)
                                    Icon(
                                        painterResource(R.drawable.metacritic_icon), "",
                                        modifier = Modifier.size(60.dp)
                                    )
                                }
                                Column(
                                    verticalArrangement = Arrangement.SpaceBetween,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Icon(Icons.Filled.AccessTime, "")
                                    Spacer (Modifier.size(4.dp))
                                    Text(notNullMovie.runtimeMins + " mins")
                                }

                            }

                            SecondHeaderText("Description")
                            Text(notNullMovie.plot, textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth())
                            SecondHeaderText("Genres")
                            Text(notNullMovie.genres, textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth())
                            SecondHeaderText("Actors")
                            LazyRow {
                                items(notNullMovie.actorList as List<ActorList>) {
                                    ActorItem(it)
                                }
                            }
                            SecondHeaderText("Countries")

                            Text("${notNullMovie.countries}", textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth())
                            //Text("Writers: ${notNullMovie.writers}", textAlign = TextAlign.Start)



                        }
                    }
                    Column(modifier = Modifier.padding(12.dp)) {
                        SecondHeaderText("Similar movies")
                        LazyRow(modifier = Modifier.padding(bottom = 8.dp)) {
                            items(notNullMovie.similars as List<Similars>) {
                                SimilarMovieItem(it) {
                                    navController.navigate("${MainActivity.MainDestinations.MOVIE_ROUTE}/${it.id}")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(18.dp))


                    }

                }
            }

        },
        sheetShape = RoundedCornerShape(12.dp),
    ) {
        Surface(color = Color.LightGray,) {


        IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.padding(2.dp)) {
            Icon(Icons.Default.ArrowBack, "")
        }
        if (selectedMovie.value == null) {
            EmptyView()
        } else {
            movie.value = selectedMovie.value
            bottomSheetPeekHeight.value = preparedBottomSheetPeekHeight

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
                modifier = Modifier.requiredHeight((2 * height / 5).toInt().dp).wrapContentWidth().padding(10.dp)

            )

        }
        }
    }
}

@Composable
fun SecondHeaderText(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
    )
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

@ExperimentalMaterialApi
@Composable
private fun SimilarMovieItem(movie: Similars, onMovieClicked: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(2.dp).requiredWidth(120.dp)
    ) {
        Card(
            modifier = Modifier.padding(4.dp),
            shape = RoundedCornerShape(8.dp), onClick = onMovieClicked
        ) {
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
                },
                modifier = Modifier.requiredHeight(150.dp).requiredWidth(100.dp)
            )
        }
        Text(movie.title, textAlign = TextAlign.Center)
    }
}
