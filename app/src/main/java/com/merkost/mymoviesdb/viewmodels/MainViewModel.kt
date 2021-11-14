package com.merkost.mymoviesdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merkost.mymoviesdb.model.entity.Top250DataDetail
import com.merkost.mymoviesdb.model.repository.MoviesRepository
import com.merkost.mymoviesdb.ui.BaseViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MoviesRepository): ViewModel() {

    val currentContent = MutableStateFlow<List<Top250DataDetail>>(listOf())

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
