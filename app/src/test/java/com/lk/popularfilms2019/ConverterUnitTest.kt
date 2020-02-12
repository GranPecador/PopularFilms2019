package com.lk.popularfilms2019

import com.lk.popularfilms2019.activities.MainActivity
import com.lk.popularfilms2019.net.models.MovieModelApi
import com.lk.popularfilms2019.presenters.MoviesPresenter
import com.lk.popularfilms2019.providers.MoviesProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterUnitTest{
    @Test
    fun convertIsCorrect(){
        val movieModelApi = MovieModelApi("The place", "Film about love", "/jfkdfjdfgjfd.jpg", "2019-10-12", 5.9)
        val moviesProvider = MoviesProvider(MoviesPresenter(MainActivity()))
        val movies = moviesProvider.converterMovies(listOf(movieModelApi))
        assertEquals("The place", movies[0].title)
        assertEquals("Film about love", movies[0].overview)
        assertEquals("https://image.tmdb.org/t/p/w500/jfkdfjdfgjfd.jpg", movies[0].posterPath)
        assertEquals("2019-10-12", movies[0].releaseDate)
    }
}