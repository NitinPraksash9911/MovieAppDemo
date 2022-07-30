package com.example.movie_demo.domain.repository

import com.example.movie_demo.domain.model.MovieData
import com.example.movie_demo.network.NetworkResource

interface IRemoteMovieRepository {
    suspend fun getMovieList(): NetworkResource<MovieData>
}