package com.example.movie_demo.data.repository

import com.example.movie_demo.data.datasource.remote.MovieApi
import com.example.movie_demo.domain.model.MovieData
import com.example.movie_demo.domain.repository.IRemoteMovieRepository
import com.example.movie_demo.network.NetworkResource
import com.example.movie_demo.network.executeRetrofitApi
import javax.inject.Inject

class RemoteMovieRepository @Inject constructor(private val movieApi: MovieApi) :
    IRemoteMovieRepository {
    override suspend fun getMovieList(): NetworkResource<MovieData> {
        return executeRetrofitApi {
            movieApi.getMoviesList()
        }
    }
}