package com.lk.popularfilms2019.presenters

import com.lk.popularfilms2019.models.MovieModel
import com.lk.popularfilms2019.providers.MoviesProvider
import com.lk.popularfilms2019.R
import com.lk.popularfilms2019.views.MoviesView

class MoviesPresenter(private var moviesView: MoviesView) {
    fun loadMovies() {
        moviesView.startLoading()

        MoviesProvider(this).loadMovies()
    }

    fun moviesLoaded(moviesList: List<MovieModel>){
        moviesView.endLoading()
        if (moviesList.isEmpty()){
            moviesView.showError(R.string.no_loaded)
        } else {
            moviesView.setupMoviesList(moviesList)
        }
    }
}