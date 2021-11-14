package com.merkost.mymoviesdb.model.repository

import android.graphics.Movie
import com.merkost.mymoviesdb.model.entity.Top250DataDetail
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getTop250Movies(): Flow<List<Top250DataDetail>>
    suspend fun getMovieDetails(movieId: String): Movie
}