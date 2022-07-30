package com.example.movie_demo.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movie_demo.databinding.MovieItemViewBinding
import com.example.movie_demo.domain.model.MovieItem
import com.example.movie_demo.utils.loadImageWithUrl

class MovieAdapter(private var itemCallback: (movieData: MovieItem) -> Unit) :
    ListAdapter<MovieItem, MovieAdapter.MovieItemViewHolder>(BreedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {

        val binding =
            MovieItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieItemViewHolder(binding, itemCallback)

    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieItemViewHolder(
        private val binding: MovieItemViewBinding,
        private val itemCallback: (movieData: MovieItem) -> Unit
    ) :
        ViewHolder(binding.root) {

        fun bind(movieData: MovieItem) {
            binding.movieIv.loadImageWithUrl(
                movieData.movieThumbnailUri
            )
            binding.movieNameTv.text = movieData.title
            binding.aboutTv.text = movieData.overview
            binding.parentLayout.setOnClickListener {
                itemCallback(movieData)
            }
        }

    }
}

class BreedDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }
}