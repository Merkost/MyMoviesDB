package com.merkost.mymoviesdb.model.entity

// result generated from /json

data class ActorList(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val asCharacter: String = ""
)

data class Movie(
    val id: String = "",
    val title: String = "",
    val originalTitle: String = "",
    val fullTitle: String = "",
    val type: String = "",
    val year: String = "",
    val image: String = "",
    val releaseDate: String = "",
    val runtimeMins: String = "",
    val runtimeStr: String = "",
    val plot: String = "",
    val plotLocal: String = "",
    val plotLocalIsRtl: Boolean?,
    val awards: String = "",
    val directors: String = "",
    val directorList: List<DirectorList>?,
    val writers: String = "",
    val writerList: List<WriterList>?,
    val stars: String = "",
    val starList: List<StarList>?,
    val actorList: List<ActorList>?,
    val fullCast: Any?,
    val genres: String = "",
    val genreList: List<GenreList>?,
    val companies: String = "",
    val companyList: List<CompanyList>?,
    val countries: String = "",
    val countryList: List<CountryList>?,
    val languages: String = "",
    val languageList: List<LanguageList>?,
    val contentRating: String = "",
    val imDbRating: String = "",
    val imDbRatingVotes: String = "",
    val metacriticRating: String = "",
    val ratings: Any?,
    val wikipedia: Any?,
    val posters: Any?,
    val images: Any?,
    val trailer: Any?,
    val boxOffice: BoxOffice?,
    val tagline: String = "",
    val keywords: String = "",
    val keywordList: List<String>?,
    val similars: List<Similars>?,
    val tvSeriesInfo: Any?,
    val tvEpisodeInfo: Any?,
    val errorMessage: String = ""
)

data class BoxOffice(
    val budget: String = "",
    val openingWeekendUSA: String = "",
    val grossUSA: String = "",
    val cumulativeWorldwideGross: String = ""
)

data class CompanyList(val id: String?, val name: String?)

data class CountryList(val key: String?, val value: String?)

data class DirectorList(val id: String?, val name: String?)

data class GenreList(val key: String?, val value: String?)

data class LanguageList(val key: String?, val value: String?)

data class Similars(
    val id: String?,
    val title: String?,
    val fullTitle: String?,
    val year: String?,
    val image: String?,
    val plot: String?,
    val directors: String?,
    val stars: String?,
    val genres: String?,
    val imDbRating: String?
)

data class StarList(val id: String?, val name: String?)

data class WriterList(val id: String?, val name: String?)
