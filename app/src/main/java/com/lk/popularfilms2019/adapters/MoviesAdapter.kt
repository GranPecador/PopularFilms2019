package com.lk.popularfilms2019.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.lk.popularfilms2019.models.MovieModel
import com.lk.popularfilms2019.activities.OnListInteractionListener
import com.lk.popularfilms2019.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_cell.view.*

class MoviesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var moviesList:ArrayList<MovieModel> = ArrayList()
    private var mListener: OnListInteractionListener? = null
    private val mOnClickListener: View.OnClickListener
    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as MovieModel
            mListener?.setNotification(item)
        }
    }
    fun setListener(listener: OnListInteractionListener){
        mListener = listener
    }
    fun setupMovies(list:ArrayList<MovieModel>){
        moviesList.clear()
        moviesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.movie_cell, parent, false)
        return MoviesViewHolder(
            itemView = itemView
        )
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MoviesViewHolder){
            holder.bind(moviesList[position])
            with(holder.notice){
                tag = moviesList[position]
                setOnClickListener(mOnClickListener)
            }
        }
    }

    class MoviesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private var title: TextView = itemView.title_item
        private var releaseDate:TextView = itemView.release_date_item
        private var poster:ImageView = itemView.poster_item
        private var overview:TextView = itemView.overview_item
        private var vote:TextView = itemView.vote_item
        var notice: MaterialButton = itemView.notification_item

        fun bind(movieModel: MovieModel){
            title.text = movieModel.title
            releaseDate.text = movieModel.releaseDate
            overview.text = movieModel.overview
            vote.text = movieModel.voteAverage.toString()
            Picasso.get().load(movieModel.posterPath).into(poster)
        }
    }
}