package com.lk.popularfilms2019.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Events
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lk.popularfilms2019.models.MovieModel
import com.lk.popularfilms2019.R
import com.lk.popularfilms2019.adapters.MoviesAdapter
import com.lk.popularfilms2019.presenters.MoviesPresenter
import com.lk.popularfilms2019.views.MoviesView

class MainActivity : AppCompatActivity(), MoviesView,
    OnListInteractionListener {

    private lateinit var progressBar:ProgressBar
    private lateinit var helloText:TextView
    private lateinit var moviesRecycler: RecyclerView

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var moviesPresenter: MoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloText = findViewById(R.id.hello_text)
        progressBar = findViewById(R.id.progress_bar)
        moviesRecycler = findViewById(R.id.movies_recycler)

        moviesAdapter = MoviesAdapter()
        moviesRecycler.adapter = moviesAdapter
        moviesRecycler.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        moviesAdapter.setListener(this)

        moviesPresenter =
            MoviesPresenter(this)
        moviesPresenter.loadMovies()
    }

    override fun showError(textResource: Int) {
        moviesRecycler.visibility = View.GONE
        helloText.visibility = View.VISIBLE
        helloText.text = getString(textResource)
    }

    override fun setupMoviesList(moviesList: List<MovieModel>) {
        moviesRecycler.visibility = View.VISIBLE
        moviesAdapter.setupMovies(moviesList)
    }

    override fun startLoading() {
        helloText.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE
        moviesRecycler.visibility = View.GONE
    }

    override fun endLoading() {
        helloText.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    override fun setNotification(movie: MovieModel) {
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(Events.CONTENT_URI)
            .putExtra(Events.TITLE, "Watch \"${movie.title}\"")
            .putExtra(Events.DESCRIPTION, movie.overview)
        startActivity(intent)
    }
}
interface OnListInteractionListener{
    fun setNotification(movie: MovieModel)
}
