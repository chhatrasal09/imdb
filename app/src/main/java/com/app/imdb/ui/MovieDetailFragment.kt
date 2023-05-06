package com.app.imdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.app.imdb.databinding.FragmentMovieDetailBinding
import com.app.imdb.model.Result
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getParcelable("data") as? Result)?.let {
            binding.movie = it
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it.backdrop_path}")
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(32)))
                .centerCrop()
                .into(binding.ivMoviePoster);
        }
    }

    companion object {
        fun newInstance(result: Result) = MovieDetailFragment().also {
            it.arguments = bundleOf("data" to result)
        }
    }
}