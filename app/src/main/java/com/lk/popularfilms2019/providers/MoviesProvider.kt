package com.lk.popularfilms2019.providers

import android.os.Handler
import com.lk.popularfilms2019.models.MovieModel
import com.lk.popularfilms2019.net.NetworkService
import com.lk.popularfilms2019.net.models.MovieModelApi
import com.lk.popularfilms2019.net.models.MoviesListApi
import com.lk.popularfilms2019.presenters.MoviesPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList

class MoviesProvider(private var presenter: MoviesPresenter) {
    fun testLoadMovies(hasMovies:Boolean){
        Handler().postDelayed({
            val moviesList:ArrayList<MovieModel> = ArrayList()
            if (hasMovies){
                moviesList.add(
                    MovieModel(
                        "Demo",
                        "About love",
                        "www",
                        "2000-5-25",
                        99.0
                    )
                )
                moviesList.add(
                    MovieModel(
                        "Demo",
                        "About love",
                        "www",
                        "2000-5-25",
                        99.0
                    )
                )
            }
            presenter.moviesLoaded(moviesList = moviesList)
        }, 2000)
    }

    fun loadMovies(){
        CoroutineScope(Dispatchers.IO).launch {
            var movieListConverted:List<MovieModel> = ArrayList()
            try {
                val list: MoviesListApi = NetworkService.instance.getMoviesPopulary(1)
                movieListConverted = converterMovies(list = list.moviesList)
            } catch (e:Exception){
                e.printStackTrace()
            }
            withContext(Dispatchers.Main){
                presenter.moviesLoaded(moviesList = movieListConverted)
            }
        }
    }
    fun converterMovies(list:List<MovieModelApi>):ArrayList<MovieModel>{
        val moviesList:ArrayList<MovieModel> = ArrayList()
        list.forEach {
            moviesList.add(
                MovieModel(
                    it.title,
                    it.overview,
                    "https://image.tmdb.org/t/p/w500${it.posterPath}",
                    it.releaseDate,
                    it.voteAvarage
                )
            )
        }
        return moviesList
    }
}