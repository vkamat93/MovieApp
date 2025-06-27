package com.example.movieapp.model

data class SearchResults(
    val description: List<Description>,
    val error_code: Int,
    val ok: Boolean
)