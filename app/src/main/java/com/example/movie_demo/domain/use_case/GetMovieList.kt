package com.example.movie_demo.domain.use_case

import com.example.movie_demo.domain.model.MovieData
import com.example.movie_demo.domain.repository.IRemoteMovieRepository
import com.example.movie_demo.network.NetworkResource


class GetMovieList(private val iRemoteMovieRepository: IRemoteMovieRepository) {

    suspend operator fun invoke(): NetworkResource<MovieData> {
        return iRemoteMovieRepository.getMovieList()
    }
}