package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class SearchResultsJW(
    val description: List<DescriptionJW>,
    @SerializedName("error_code") val errorCode: Int,
    val ok: Boolean
)