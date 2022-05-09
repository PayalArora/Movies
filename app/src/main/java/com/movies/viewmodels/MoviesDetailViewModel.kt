package com.news.headlines.viewmodels

import androidx.lifecycle.*
import com.data.repository.repository.MoviesDataRepository
import com.movies.response.MovieDetail
import com.movies.response.MoviesData
import com.movies.response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDetailViewModel @Inject constructor(private val repository: MoviesDataRepository) : ViewModel() {
    private val TAG = MoviesDetailViewModel::class.java.simpleName
    private val _DetailResponse: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()
    val moviesResponse: LiveData<Resource<MovieDetail>>
        get() = _DetailResponse

    fun getMovies(
        id: String,
    ) = viewModelScope.launch {
        _DetailResponse.value = repository.getMoviesDetail(id)
    }
}

