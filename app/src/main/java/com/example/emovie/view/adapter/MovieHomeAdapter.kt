package com.example.emovie.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emovie.databinding.ItemMovieBinding
import com.example.emovie.model.Result
import com.example.emovie.view.viewholder.MovieViewHolder

class MovieHomeAdapter(private val movies: ArrayList<Result>, private val listener: MovieAdapterListener) : RecyclerView.Adapter<MovieViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(context,movies[position])
        holder.itemView.setOnClickListener {
            listener.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    interface MovieAdapterListener {
        fun onClick(movie: Result)
    }
}