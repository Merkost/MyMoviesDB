package com.merkost.mymoviesdb.di

import com.merkost.mymoviesdb.model.api.MovieDatabaseApiService
import com.merkost.mymoviesdb.model.datasource.MoviesRepositoryRetrofitImpl
import com.merkost.mymoviesdb.model.repository.MoviesRepository
import com.merkost.mymoviesdb.viewmodels.MainViewModel
import com.merkost.mymoviesdb.viewmodels.MovieDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<MoviesRepository> { MoviesRepositoryRetrofitImpl() }
}

val mainActivity = module {
    viewModel { MainViewModel(get()) }
    viewModel { MovieDetailsViewModel() }
}