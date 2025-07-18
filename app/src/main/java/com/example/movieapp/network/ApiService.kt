package com.example.movieapp.network

import com.example.movieapp.model.SearchResultsJW
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("justwatch")
    suspend fun getSearchResults(
        @Query("q") query: String,
    ): SearchResultsJW

    companion object {
        private var apiService: ApiService? = null
        fun getInstance(): ApiService? {
            // Implementation to create and return an instance of ApiService
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://imdb.iamidiotareyoutoo.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService
        }
    }
}