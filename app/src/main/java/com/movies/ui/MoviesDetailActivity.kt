package com.movies.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.data.repository.utils.ID
import com.data.repository.utils.getProgressDrawable
import com.data.repository.utils.hideProgressBar
import com.data.repository.utils.showProgressBar
import com.movies.databinding.ActivityMovieDetailBinding
import com.movies.response.MovieDetail
import com.movies.response.Resource
import com.news.headlines.ui.adapter.MoviesAdapter
import com.news.headlines.viewmodels.MoviesDetailViewModel
import com.news.headlines.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MoviesDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra(ID)?.let { viewModel.getMovies(it) }
        showProgressBar()
        viewModel.moviesResponse.observe(this, {
            when (it) {
                is Resource.Success -> {
                    Log.d(ContentValues.TAG, "Success Data::${it.value}")
                    updateUi(it.value)
                    hideProgressBar()
                }
                is Resource.Failure -> {
                    Log.d(ContentValues.TAG, "Failure Data::${it.errorBody}")
                    hideProgressBar()
                }
            }
        })
    }

    private fun updateUi(movieDetail: MovieDetail) {

        val circularProgressDrawable = getProgressDrawable()
        circularProgressDrawable.start()
        if (movieDetail.Poster != null) {
            Glide.with(this).load(movieDetail.Poster)
                .placeholder(circularProgressDrawable).into(binding.imageView)
        } else
            Glide.with(this).load(circularProgressDrawable)
                .into(binding.imageView)
        binding.backImg.setOnClickListener { onBackPressed() }

        binding.txtTitle.setText(movieDetail.Title)
        binding.txtRating.setText(movieDetail.imdbRating)
        binding.txtTime.setText(movieDetail.Runtime)
        binding.txtHologramn.setText(movieDetail.Type)
        if (movieDetail.Genre?.split(",")?.size?:0 > 1){
            binding.txtAction.setText(movieDetail.Genre?.split(",")?.get(0) ?: "")
            binding.txtAdventure.setText(movieDetail.Genre?.split(",")?.get(1) ?: "")
        } else
        binding.txtAction.setText(movieDetail.Genre)
        binding.txtDesc.setText(movieDetail.Plot)
        binding.txtDirector.setText("Director: ${movieDetail.Director}")
        binding.txtWriter.setText("Writer: ${movieDetail.Writer}")
        binding.txtActors.setText("Actors: ${movieDetail.Actors}")


    }
}