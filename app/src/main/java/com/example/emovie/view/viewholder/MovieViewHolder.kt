package com.example.emovie.view.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emovie.databinding.ItemMovieBinding
import com.example.emovie.model.Result
import com.example.emovie.utilities.Util

class MovieViewHolder(binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {

    var binding: ItemMovieBinding

    init {
        this.binding = binding
    }

    fun bind(context: Context?, result: Result){
        result.poster_path?.let { image ->
            if (context != null) {
                Glide.with(context).load(Util.formatImageUrl(image)).fitCenter()
                    .into(binding.imageMovie)
            }
        }
    }
}