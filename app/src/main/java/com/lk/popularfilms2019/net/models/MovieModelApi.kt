package com.lk.popularfilms2019.net.models

import com.google.gson.annotations.SerializedName

data class MovieModelApi (@SerializedName("title") val title:String,
                          @SerializedName("overview") val overview:String,
                          @SerializedName("poster_path") val posterPath:String,
                          @SerializedName("release_date") val releaseDate:String,
                          @SerializedName("vote_average") val voteAvarage:Double
                )