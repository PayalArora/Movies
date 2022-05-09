package com.news.headlines.viewmodels

import androidx.lifecycle.*
import com.data.repository.repository.MoviesDataRepository
import com.movies.response.MoviesData
import com.movies.response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesDataRepository) : ViewModel() {
    private val TAG = MoviesViewModel::class.java.simpleName
    private val _moviesResponse: MutableLiveData<Resource<MoviesData>> = MutableLiveData()
    val moviesResponse: LiveData<Resource<MoviesData>>
        get() = _moviesResponse

    fun getMovies(
        category: String,
    ) = viewModelScope.launch {
        _moviesResponse.value = repository.getMovies(category)
    }
}

