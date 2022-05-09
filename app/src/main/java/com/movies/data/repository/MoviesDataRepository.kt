package com.data.repository.repository

import android.util.Log
import com.data.repository.Resource.BaseRepository
import com.data.repository.utils.API_KEY
import com.movies.data.repository.MovieApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class MoviesDataRepository @Inject constructor(
    private var apiService: MovieApi,
) : BaseRepository() {

    val TAG = MoviesDataRepository::class.java.simpleName

    val coroutineErrorHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, exception.printStackTrace().toString())
    }
    suspend fun getMoviesDetail(
        id: String
    ) = safeApiCall {
        apiService.getMoviesDetail(id, API_KEY)
    }
    suspend fun getMovies(
        category: String
    ) = safeApiCall {
        apiService.getMovies(category, API_KEY)
    }
}
