package com.merkost.mymoviesdb.model.repository

import com.merkost.mymoviesdb.model.entity.Movie
import com.merkost.mymoviesdb.model.entity.Top250DataDetail
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getTop250Movies(): Flow<List<Top250DataDetail>>
    suspend fun getMovieDetails(movieId: String): Flow<Movie>
}