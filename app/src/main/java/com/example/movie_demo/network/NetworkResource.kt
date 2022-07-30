package com.example.movie_demo.network

sealed class NetworkResource<out T> {
    data class Success<out T>(val data: T) : NetworkResource<T>()
    data class Error<out T>(
        val errorResponse: ErrorResponse
    ) : NetworkResource<T>()
}


