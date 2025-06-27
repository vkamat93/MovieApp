package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.DescriptionJW
import com.example.movieapp.network.ApiService
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    private val _movieListResponse: MutableLiveData<List<DescriptionJW>> by lazy {
        MutableLiveData<List<DescriptionJW>>() }
    val movieListResponse: LiveData<List<DescriptionJW>> = _movieListResponse

    private val _errorMsg: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val errorMsg: LiveData<String> = _errorMsg

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMovieList() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val movieList = apiService?.getSearchResults("game of")
                // Check if the API call was successful and the response is not null
                if (movieList != null) {
                    Log.d("MovieViewModel", "Response: ${movieList.errorCode}")
                    Log.d("MovieViewModel", "Response: ${movieList.description[2]}")
                    _movieListResponse.postValue(movieList.description)
                } else {
                    _errorMsg.postValue("No data found")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMsg.postValue(e.toString())
            }
            _isLoading.postValue(false)
        }
    }
}