package com.example.movie_demo.arch

import com.example.movie_demo.network.ErrorResponse

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Error(val errorResponse: ErrorResponse) : ViewState<Nothing>()
    data class Success<T>(val item: T) : ViewState<T>()
}

