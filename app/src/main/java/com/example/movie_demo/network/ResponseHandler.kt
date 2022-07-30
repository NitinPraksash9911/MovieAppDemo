package com.example.movie_demo.network


import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> executeRetrofitApi(call: suspend () -> Response<T>): NetworkResource<T> {
    return try {
        val response = call()
        when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null) {
                    NetworkResource.Success(body)
                } else {
                    NetworkResource.Error(
                        ErrorResponse(
                            responseCode = response.code(),
                            retrofitErrorResponse = response.errorBody(),
                            errorStatus = ErrorStatus.ServerError
                        )
                    )
                }
            }
            else -> {
                NetworkResource.Error(
                    ErrorResponse(
                        responseCode = response.code(),
                        retrofitErrorResponse = response.errorBody(),
                        errorStatus = ErrorStatus.InvalidError
                    )
                )
            }
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.NetworkError
                    )
                )
            }
            is HttpException -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.HTTPError
                    )
                )
            }
            else -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.GotException
                    )
                )
            }
        }
    }
}





