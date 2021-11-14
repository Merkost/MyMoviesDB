package com.merkost.mymoviesdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merkost.mymoviesdb.model.entity.Movie
import com.merkost.mymoviesdb.model.entity.Top250DataDetail
import com.merkost.mymoviesdb.model.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel(val repository: MoviesRepository) : ViewModel() {
    val currentContent = MutableStateFlow<Movie?>(null)

    fun getMovieById(movieId: String?) {
        movieId?.let {
            viewModelScope.launch {
                repository.getMovieDetails(movieId).collect {
                    currentContent.value = it
                }
            }
        }

    }
}
