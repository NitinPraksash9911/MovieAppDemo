package com.example.movie_demo.presentation.movie_list

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.catcraft.arch.BaseFragment
import com.example.movie_demo.arch.ViewState
import com.example.movie_demo.databinding.FragmentMovieListBinding
import com.example.movie_demo.domain.model.MovieItem
import com.example.movie_demo.utils.hide
import com.example.movie_demo.utils.launchAndCollectIn
import com.example.movie_demo.utils.show
import com.example.movie_demo.utils.snack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding>
    (FragmentMovieListBinding::inflate) {

    private val movieModel by viewModels<MovieListViewModel>()

    private lateinit var movieAdapter: MovieAdapter
    override fun initViews(savedInstanceState: Bundle?) {

        movieAdapter = MovieAdapter(::onListItemClick)

        binding.movieListRv.adapter = movieAdapter
        initObserver()
        binding.pullToRefresh.setOnRefreshListener {
            movieModel.fetchMovies()
            binding.pullToRefresh.isRefreshing = false
        }
    }

    private fun onListItemClick(movieData: MovieItem) {
        val directions =
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movieData)
        findNavController().navigate(directions)
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun initObserver() {
        movieModel.movieListState.launchAndCollectIn(this, Lifecycle.State.STARTED) { movieData ->
            when (movieData) {
                is ViewState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.movieListRv.hide()
                    binding.errorTv.hide()
                }
                is ViewState.Success -> {
                    binding.progressCircular.visibility = View.GONE
                    binding.errorTv.hide()
                    binding.movieListRv.show()
                    movieAdapter.submitList(movieData.item.movieItems)
                }
                is ViewState.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    binding.movieListRv.hide()
                    binding.errorTv.show()
                    movieData.errorResponse.exception.toString().snack(Color.RED, binding.root)
                }

            }
        }

    }
}