package com.merkost.mymoviesdb.model.entity

import com.google.gson.annotations.SerializedName


data class MoviesResult (
    val items: List<Top250DataDetail> = listOf(),
    val errorMessage: String = ""
)