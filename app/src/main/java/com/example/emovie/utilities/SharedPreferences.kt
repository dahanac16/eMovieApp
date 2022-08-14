package com.example.emovie.utilities

import android.content.Context
import android.content.SharedPreferences
import com.example.emovie.model.MovieModel
import com.example.emovie.model.Result
import com.google.gson.Gson

object SharedPreferences {


    fun saveMoviesTrend(context: Context, movie: MovieModel) {
        val json = Gson()
        val jsonMovieModel = json.toJson(movie)
        getSharedPreferences(context).edit().putString("getMoviesTrend", jsonMovieModel).apply()
    }

    fun getMoviesTrend(context: Context): String? {
        return getSharedPreferences(context).getString("getMoviesTrend", null)
    }

    fun getMoviesTrendFormat(context: Context): MovieModel?{
        val movie = getSharedPreferences(context).getString("getMoviesTrend", "")

        if(movie.isNullOrEmpty())
            return null

        return Gson().fromJson(movie, MovieModel::class.java)
    }

    fun saveMoviesNextReleases(context: Context, movie: MovieModel) {
        val json = Gson()
        val jsonMovieModel = json.toJson(movie)
        getSharedPreferences(context).edit().putString("getMoviesNextReleases", jsonMovieModel).apply()
    }

    fun getMoviesNextReleases(context: Context): String? {
        return getSharedPreferences(context).getString("getMoviesNextReleases", null)
    }

    fun getMoviesNextReleasesFormat(context: Context): MovieModel?{
        val movie = getSharedPreferences(context).getString("getMoviesNextReleases", "")

        if(movie.isNullOrEmpty())
            return null

        return Gson().fromJson(movie, MovieModel::class.java)
    }

    fun saveMoviesFilterIdiom(context: Context, movie: MovieModel) {
        val json = Gson()
        val jsonMovieModel = json.toJson(movie)
        getSharedPreferences(context).edit().putString("getMoviesFilterIdiom", jsonMovieModel).apply()
    }

    fun getMoviesFilterIdiom(context: Context): String? {
        return getSharedPreferences(context).getString("getMoviesFilterIdiom", null)
    }

    fun getMoviesFilterIdiomFormat(context: Context): MovieModel?{
        val movie = getSharedPreferences(context).getString("getMoviesFilterIdiom", "")

        if(movie.isNullOrEmpty())
            return null

        return Gson().fromJson(movie, MovieModel::class.java)
    }

    fun saveMoviesFilterDate(context: Context, movie: MovieModel) {
        val json = Gson()
        val jsonMovieModel = json.toJson(movie)
        getSharedPreferences(context).edit().putString("getMoviesFilterDate", jsonMovieModel).apply()
    }

    fun getMoviesFilterDate(context: Context): String? {
        return getSharedPreferences(context).getString("getMoviesFilterDate", null)
    }

    fun getMoviesFilterDateFormat(context: Context): MovieModel?{
        val movie = getSharedPreferences(context).getString("getMoviesFilterDate", "")

        if(movie.isNullOrEmpty())
            return null

        return Gson().fromJson(movie, MovieModel::class.java)
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("data", Context.MODE_PRIVATE)
    }
}