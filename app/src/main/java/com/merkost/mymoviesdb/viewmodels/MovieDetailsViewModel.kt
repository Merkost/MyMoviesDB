package com.merkost.mymoviesdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merkost.mymoviesdb.model.entity.Top250DataDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {
    val currentContent = MutableStateFlow<Movie>(listOf())

    init {
        getTop250Movies()
    }

    private fun getTop250Movies() {
        viewModelScope.launch {
            repository.getTop250Movies().collect {
                if (it.isNotEmpty()) currentContent.value = it
            }
        }
    }
}
