package com.example.emovie.view.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emovie.R
import com.example.emovie.databinding.ItemMovieForGritBinding
import com.example.emovie.model.Result
import com.example.emovie.utilities.Util

class MovieForGritViewHolder(binding: ItemMovieForGritBinding): RecyclerView.ViewHolder(binding.root) {

    var binding: ItemMovieForGritBinding

    init {
        this.binding = binding
    }

    fun bind(context: Context?, result: Result){
        result.poster_path?.let { image ->
            if (context != null && image.isNotEmpty()) {
                Glide.with(context).load(Util.formatImageUrl(image)).fitCenter()
                    .into(binding.imageMovie)
            }
        }
        if (result.poster_path == null) {
            binding.imageMovie.setImageResource(R.drawable.image_default)
        }
    }
}