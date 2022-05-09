package com.movies.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.data.repository.utils.ID
import com.data.repository.utils.hideProgressBar
import com.data.repository.utils.showProgressBar
import com.movies.databinding.ActivityMoviesBinding
import com.movies.databinding.ToolbarMainBinding
import com.movies.response.Resource
import com.movies.response.Search
import com.news.headlines.ui.adapter.MoviesAdapter
import com.news.headlines.ui.adapter.OnItemClickPosition
import com.news.headlines.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity(),  OnItemClickPosition {
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var toolBarBinding: ToolbarMainBinding
    private val viewModel: MoviesViewModel by viewModels()
    var moviesData: List<Search>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        toolBarBinding = binding.toolbar
        setContentView(binding.root)
        setSupportActionBar(toolBarBinding.toolbarMain)

        binding.recyclerMovies.apply {
            layoutManager = GridLayoutManager(this@MovieActivity, 3)
            adapter = MoviesAdapter(this@MovieActivity)
        }
        binding.searchText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (v.text.length != 0 && actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getMovies(binding.searchText.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

        viewModel.getMovies("har")
        showProgressBar()
        viewModel.moviesResponse.observe(this, {
            when (it) {
                is Resource.Success -> {
                    Log.d(TAG, "Success Data::${it.value}")
                    (binding.recyclerMovies.adapter as MoviesAdapter).apply {
                        moviesData = it.value.Search
                        response = moviesData
                        notifyDataSetChanged()
                    }
                    hideProgressBar()
                }
                is Resource.Failure -> {
                    Log.d(TAG, "Failure Data::${it.errorBody}")
                    Toast.makeText(this,"${it.errorBody}", Toast.LENGTH_LONG).show()
                    hideProgressBar()
                }
            }
        })
    }

    override fun OnClick(position: Int) {
        moviesData?.get(position).let {
            val intent = Intent(this, MoviesDetailActivity::class.java)
            intent.putExtra(ID,moviesData?.get(position)?.imdbID)
            startActivity(intent)
        }
    }
}