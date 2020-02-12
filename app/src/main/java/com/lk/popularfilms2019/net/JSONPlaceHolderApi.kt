package com.lk.popularfilms2019.net

import com.lk.popularfilms2019.net.models.MoviesListApi
import retrofit2.http.GET
import retrofit2.http.Query

interface JSONPlaceHolderApi {

    @GET("discover/movie?sort_by=popularity.desc&primary_release_year=2019&api_key=9d9b2a543a1803951ac16670d61122a8")
    suspend fun getMoviesPopulary(@Query("page") number:Int): MoviesListApi
}