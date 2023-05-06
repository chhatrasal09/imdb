package com.app.imdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.imdb.databinding.ListItemMovieBinding
import com.app.imdb.model.Result
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieListRecyclerAdapter :
    PagingDataAdapter<Result, MovieListRecyclerAdapter.ViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position) ?: return)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    class ViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Result) {
            binding.tvMovieName.text = data.title
            binding.tvMovieDescription.text = data.overview
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${data.backdrop_path}")
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(32)))
                .centerCrop()
                .into(binding.ivMovieThumbnail);
        }

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }
}