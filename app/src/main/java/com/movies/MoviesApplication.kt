package com.movies

import android.app.Application

import dagger.hilt.android.HiltAndroidApp
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
@HiltAndroidApp
class MoviesApplication : Application() {
    private val mTAG = MoviesApplication::class.java.simpleName


}