package com.example.emovie.view.adapter

import com.example.emovie.databinding.ItemMovieForGritBinding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emovie.model.Result
import com.example.emovie.view.viewholder.MovieForGritViewHolder

class MovieHomeForGritAdapter(private val movies: ArrayList<Result>, private val listener: MovieHomeAdapter.MovieAdapterListener) : RecyclerView.Adapter<MovieForGritViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieForGritViewHolder {
        context = parent.context
        val binding = ItemMovieForGritBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieForGritViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieForGritViewHolder, position: Int) {
        holder.bind(context,movies[position])
        holder.itemView.setOnClickListener {
            listener.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size
}
