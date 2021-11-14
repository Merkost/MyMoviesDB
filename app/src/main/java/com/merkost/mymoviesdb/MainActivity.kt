package com.merkost.mymoviesdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.merkost.mymoviesdb.ui.compose.MainScreen
import com.merkost.mymoviesdb.ui.compose.MovieDetailsScreen
import com.merkost.mymoviesdb.ui.theme.MyMoviesDBTheme

class MainActivity : ComponentActivity() {


    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoviesDBTheme {
                Navigation()
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = MainDestinations.HOME_ROUTE
        ) {
            composable(
                route = MainDestinations.HOME_ROUTE,
            ) {
                MainScreen(navController = navController)
            }
            composable(
                route = "${MainDestinations.MOVIE_ROUTE}/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.StringType }),
            ) { backStackEntry ->
                MovieDetailsScreen(
                    navController = navController,
                    backStackEntry.arguments?.getString("movieId")
                )
            }
        }
    }

    object MainDestinations {
        const val HOME_ROUTE = "home"
        const val MOVIE_ROUTE = "movie_details"
    }

}