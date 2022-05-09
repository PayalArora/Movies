package com.movies.data.repository

import com.movies.response.MovieDetail
import com.movies.response.MoviesData
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
    //https://omdbapi.com/?s=har&apikey=a0783fa9

    @GET(".")
    suspend fun getMovies(
        @Query("s") category: String,
        @Query("apiKey") apiKey: String
    ): MoviesData

    @GET(".")
    suspend fun getMoviesDetail(
        @Query("i") id: String,
        @Query("apiKey") apiKey: String
    ): MovieDetail
}