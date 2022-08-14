package com.example.emovie.view.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.emovie.R
import com.example.emovie.databinding.FragmentDetailMovieBinding
import com.example.emovie.model.Result
import com.example.emovie.view.activity.MainActivity
import com.example.emovie.viewmodel.DetailMovieViewModel


class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding
    private var movie: Result? = null
    private val viewModel: DetailMovieViewModel by viewModels()
    lateinit var video: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMyArguments()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    fun init() {
        validateInitSetup()
        initOnClick()
    }

    private fun validateInitSetup() {
        movie?.let { initSetUp(it) }
    }

    private fun getMyArguments(){
        try {
            arguments?.let {
                movie = it.getSerializable("movie") as Result
            }
        } catch (e: Exception) {
            Log.e("Exceptions arguments", e.toString())
        }
    }

    private fun initSetUp(movie: Result) {
        with(binding) {
            val image = movie.poster_path ?: ""
            val formatImage = if (image.isNotEmpty()) viewModel.getFormatUrl(image) else image
            val title = movie.title
            val movieDescription = movie.overview ?: ""
            val stringMovieDescription = movieDescription.ifEmpty { getString(R.string.not_description) }
            val rating = movie.vote_average ?: ""
            val date = movie.release_date ?: ""

            video = videoinapp.video
            titleMovie.text = title
            dateText.text = date.substring(0,4)
            ratingText.text = rating.toString()
            description.text = stringMovieDescription
            if (formatImage == "") {
                imageView.setImageResource(R.drawable.image_default)
            } else {
                Glide.with(requireContext()).load(formatImage).into(imageView)
            }
        }

    }

    private fun initOnClick() {

        binding.back.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }

        binding.bottomPlayVideo.setOnClickListener {
            when (movie?.id) {
                616037F -> {
                    createInstanceForVideo(R.raw.thor)
                }
                453395F -> {
                    createInstanceForVideo(R.raw.strange)
                }

            }
        }

        binding.videoinapp.imageviewClose.setOnClickListener {
            video.stopPlayback()
            video.seekTo(0)
            binding.constraintVideo.visibility = View.GONE
        }
    }

    private fun createInstanceForVideo(raw: Int) {
        binding.constraintVideo.visibility = View.VISIBLE
        val uri: Uri = Uri.parse("android.resource://com.example.emovie/$raw")
        video.setMediaController(MediaController(requireContext()))
        video.setVideoURI(uri)
        video.requestFocus()
        video.start()
    }
}