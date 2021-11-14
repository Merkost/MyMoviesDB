package com.merkost.mymoviesdb.model.entity


data class Top250DataDetail (
    val id: String = "",
    val rank: String = "",
    val title: String = "",
    val fullTitle: String = "",
    val year: String = "",
    val image: String = "",
    val crew: String = "",
    val imdbRating: String = "",
    val imdbRatingCount: String = "",
)
