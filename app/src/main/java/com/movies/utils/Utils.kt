package com.data.repository.utils

import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.movies.R
import java.text.SimpleDateFormat
import java.util.*

const val  API_KEY = "a0783fa9"
const val  ID = "ID"
// -------- Progress Bar -----//

fun Activity.hideProgressBar() {
    ProgressUtil.hideLoading()
}
fun Activity.showProgressBar() {
    ProgressUtil.showLoading(this)
}

fun Context.getProgressDrawable(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.progressRotation = 0.8f
    circularProgressDrawable.setColorSchemeColors(ResourcesCompat.getColor(this.resources,
        R.color.white,
        null))
    return circularProgressDrawable
}

