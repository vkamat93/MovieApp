package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class DescriptionImdb(

    @SerializedName("#ACTORS") val actors: String,
    @SerializedName("#AKA") val aka: String,
    @SerializedName("#IMDB_ID") val imdbId: String,
    @SerializedName("#IMDB_IV") val imdbIv: String,
    @SerializedName("#IMDB_URL") val imdbUrl: String,
    @SerializedName("#IMG_POSTER") val imgPoster: String,
    @SerializedName("#RANK") val rank: Int,
    @SerializedName("#TITLE") val title: String,
    @SerializedName("#YEAR") val year: Int,
    @SerializedName("photo_height") val photoHeight: Int,
    @SerializedName("photo_width") val photoWidth: Int
)