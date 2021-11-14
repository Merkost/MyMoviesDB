package com.merkost.mymoviesdb.model.api

import com.merkost.mymoviesdb.model.entity.Movie
import com.merkost.mymoviesdb.model.entity.MoviesResult
import com.merkost.mymoviesdb.model.entity.Top250DataDetail
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseApiService {

    @GET("Top250Movies/{api_key}")
    suspend fun getTop250Movies(
        @Path("api_key") apiKey: String = "k_6gmt9cas",

    ): MoviesResult

    @GET("title/{api_key}/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Path("api_key") apiKey: String = "k_6gmt9cas",
    ): Movie

}

