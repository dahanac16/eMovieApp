package com.example.emovie.utilities

object Constants {

    class Home {
        companion object{
            const val QUERYTREND = "discover/movie?sort_by=popularity.desc&api_key=1b800fead5daa434c517992ca4cd3f58"
            const val QUERYFILTERDATE = "discover/movie?primary_release_year=1993&sort_by=vote_average.desc&api_key=1b800fead5daa434c517992ca4cd3f58"
            const val QUERYFILTERIDIOM = "discover/movie?riginal_language=es&api_key=1b800fead5daa434c517992ca4cd3f58"
            const val QUERYNEXTRELEASES = "discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22&api_key=1b800fead5daa434c517992ca4cd3f58"
        }
    }

    class Image {
        companion object{
            const val BASEIMAGEURL = "https://image.tmdb.org/t/p/w500"
        }
    }
}