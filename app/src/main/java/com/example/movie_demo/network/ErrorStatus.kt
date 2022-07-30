package com.example.movie_demo.network

sealed class ErrorStatus {
    object HTTPError : ErrorStatus()
    object NetworkError : ErrorStatus()
    object GotException : ErrorStatus()
    object InvalidError : ErrorStatus()
    object ServerError : ErrorStatus()
}