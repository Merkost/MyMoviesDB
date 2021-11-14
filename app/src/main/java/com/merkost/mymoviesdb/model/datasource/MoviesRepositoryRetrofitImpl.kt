package com.merkost.mymoviesdb.model.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.merkost.mymoviesdb.model.api.MovieDatabaseApiService
import com.merkost.mymoviesdb.model.entity.Top250DataDetail
import com.merkost.mymoviesdb.model.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRepositoryRetrofitImpl: MoviesRepository {
    companion object {
        private const val BASE_MOVIES_URL = "https://imdb-api.com/en/API/"
    }

    override suspend fun getTop250Movies(): Flow<List<Top250DataDetail>> = flow {
        val moviesResult = getService().getTop250Movies()
        emit(moviesResult.items)
    }


    override suspend fun getMovieDetails(movieId: String) = getService().getMovieDetails(movieId)

    private fun getService(): MovieDatabaseApiService {
        return createRetrofit().create(MovieDatabaseApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_MOVIES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

}