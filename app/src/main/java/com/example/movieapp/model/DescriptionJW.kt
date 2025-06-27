package com.example.movieapp.model

data class DescriptionJW(
    val backdrops: List<String>,
    val id: String,
    val imdbId: String,
    val jwRating: Double,
    val offers: List<Offer>,
    val photo_url: List<String>,
    val runtime: Int,
    val title: String,
    val tmdbId: String,
    val tomatoCertifiedFresh: Boolean,
    val tomatoMeter: Int,
    val type: String,
    val url: String,
    val year: Int
)