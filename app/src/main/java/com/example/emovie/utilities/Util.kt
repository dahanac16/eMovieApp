package com.example.emovie.utilities

object Util {

    fun formatImageUrl(endPointImage: String):  String{
        val baseUrl = Constants.Image.BASEIMAGEURL
        return baseUrl + endPointImage
    }
}