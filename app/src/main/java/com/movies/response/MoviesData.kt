package com.movies.response

import com.google.gson.annotations.SerializedName


data class MoviesData (

  @SerializedName("Search"       ) var Search       : ArrayList<Search> = arrayListOf(),
  @SerializedName("totalResults" ) var totalResults : String?           = null,
  @SerializedName("Response"     ) var Response     : String?           = null

)