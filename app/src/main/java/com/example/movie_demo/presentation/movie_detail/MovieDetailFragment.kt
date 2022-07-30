package com.example.movie_demo.presentation.movie_detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.example.catcraft.arch.BaseFragment
import com.example.movie_demo.databinding.FragmentMovieDetailBinding
import com.example.movie_demo.domain.model.MovieItem
import com.example.movie_demo.utils.loadImageWithUrl


class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>
    (FragmentMovieDetailBinding::inflate) {

    private val navArgument: MovieDetailFragmentArgs by navArgs()

    override fun initViews(savedInstanceState: Bundle?) {
        val breadDetail = navArgument.movieDetail
        setUpDetails(breadDetail)
    }

    private fun setUpDetails(movieDetail: MovieItem) {
        binding.movieIv.loadImageWithUrl(movieDetail.movieThumbnailUri)
        binding.movieNameTv.text = movieDetail.title
        binding.overviewTv.text = movieDetail.overview
    }
}