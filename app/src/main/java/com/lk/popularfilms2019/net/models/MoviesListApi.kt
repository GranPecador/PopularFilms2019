package com.lk.popularfilms2019.net.models

import com.google.gson.annotations.SerializedName

data class MoviesListApi(@SerializedName("page") val page:Int,
                         @SerializedName("total_results") val totalResults:Int,
                         @SerializedName("total_pages") val totalPages: Int,
                         @SerializedName("results") val moviesList:ArrayList<MovieModelApi>)