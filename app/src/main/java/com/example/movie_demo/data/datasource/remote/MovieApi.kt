package com.example.movie_demo.data.datasource.remote

import com.example.movie_demo.domain.model.MovieData
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("3/movie/popular")
    suspend fun getMoviesList(): Response<MovieData>
}