package com.lk.popularfilms2019.views

import com.lk.popularfilms2019.models.MovieModel

interface MoviesView {
    fun showError(textResource:Int)
    fun setupMoviesList(moviesList:List<MovieModel>)
    fun startLoading()
    fun endLoading()
}