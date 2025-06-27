package com.example.movieapp.model

data class SearchResultsImdb(
    val descriptionImdb: List<DescriptionImdb>,
    val error_code: Int,
    val ok: Boolean
)