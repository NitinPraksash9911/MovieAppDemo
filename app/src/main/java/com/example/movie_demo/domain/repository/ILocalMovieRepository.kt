package com.example.movie_demo.domain.repository

import com.example.movie_demo.domain.model.MovieData
import kotlinx.coroutines.flow.Flow

interface ILocalMovieRepository {
    suspend fun getMovieList(): Flow<MovieData>
}