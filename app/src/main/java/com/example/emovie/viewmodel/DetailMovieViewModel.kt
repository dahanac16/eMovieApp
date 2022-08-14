package com.example.emovie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.emovie.utilities.Util

class DetailMovieViewModel(app: Application): AndroidViewModel(app) {

    fun getFormatUrl(endPointImage: String): String{
        return Util.formatImageUrl(endPointImage)
    }
}