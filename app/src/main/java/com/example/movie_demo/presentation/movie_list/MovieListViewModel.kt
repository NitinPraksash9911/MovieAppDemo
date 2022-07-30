package com.example.movie_demo.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_demo.arch.ViewState
import com.example.movie_demo.domain.model.MovieData
import com.example.movie_demo.domain.use_case.GetMovieList
import com.example.movie_demo.network.NetworkResource
import com.example.movie_demo.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieList: GetMovieList,
    private val coroutineDispatcher: DispatcherProvider
) : ViewModel() {

    private var _movieListState =
        MutableStateFlow<ViewState<MovieData>>(ViewState.Loading)
    val movieListState: StateFlow<ViewState<MovieData>> = _movieListState

    init {
        fetchMovies()
    }

     fun fetchMovies() {
        viewModelScope.launch(coroutineDispatcher.main) {
            when (val result = getMovieList.invoke()) {
                is NetworkResource.Success -> {
                    _movieListState.value = ViewState.Success(result.data)
                }
                is NetworkResource.Error -> {
                    _movieListState.value = ViewState.Error(result.errorResponse)
                }
            }

        }
    }

}