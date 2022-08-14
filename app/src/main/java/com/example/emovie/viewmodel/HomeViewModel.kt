package com.example.emovie.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.emovie.network.APIServiceInterface
import com.example.emovie.model.MovieModel
import com.example.emovie.utilities.SharedPreferences
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel(app:Application): AndroidViewModel(app){

    private val context = app
    private val sharedPreferences = SharedPreferences

    private val _successTrend = MutableLiveData<MovieModel>()
    val successTrend: LiveData<MovieModel>
        get() = _successTrend

    private val _successNextReleases = MutableLiveData<MovieModel>()
    val successNextReleases: LiveData<MovieModel>
        get() = _successNextReleases

    private val _successFilter = MutableLiveData<MovieModel>()
    val successFilter: LiveData<MovieModel>
        get() = _successFilter

    private val _errorTrend = MutableLiveData(false)
    val errorTrend: LiveData<Boolean>
        get() = _errorTrend

    private val _errorNextReleases = MutableLiveData(false)
    val errorNextReleases: LiveData<Boolean>
        get() = _errorNextReleases

    private val _errorFilter = MutableLiveData(false)
    val errorFilter: LiveData<Boolean>
        get() = _errorFilter

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchByTrend(query:String){
        if (sharedPreferences.getMoviesTrend(context) == null) {
            viewModelScope.launch {
                val call = getRetrofit().create(APIServiceInterface::class.java).getMovieByTrend(query)
                val movies = call.body()
                run {
                    if (call.isSuccessful) {
                        movies?.let {
                            _successTrend.postValue(it)
                            saveDataTrend(it)
                        }
                    } else {
                        _errorTrend.postValue(true)
                    }
                }

            }
        } else {
            val movie = sharedPreferences.getMoviesTrendFormat(context)
            movie?.let { _successTrend.postValue(it) }
            if (movie == null) _errorTrend.postValue(true)
        }
    }

    fun searchByNextReleases(query:String){
        if (sharedPreferences.getMoviesNextReleases(context) == null) {
            viewModelScope.launch {
                val call = getRetrofit().create(APIServiceInterface::class.java)
                    .getMovieByTrend(query)
                val movies = call.body()
                run {
                    if (call.isSuccessful) {
                        movies?.let {
                            _successNextReleases.postValue(it)
                            saveDataNextReleases(it)
                        }
                    } else {
                        _errorNextReleases.postValue(true)
                    }
                }

            }
        } else {
            val movie = sharedPreferences.getMoviesNextReleasesFormat(context)
            movie?.let { _successNextReleases.postValue(it) }
            if (movie == null) _errorNextReleases.postValue(true)
        }
    }

    fun searchByFilter(query:String, isForIdiom: Boolean){
        if (isForIdiom) {
            if (sharedPreferences.getMoviesFilterIdiom(context) == null) {
                getFilter(query, isForIdiom)
            } else {
                val movie = sharedPreferences.getMoviesFilterIdiomFormat(context)
                setInfoFilter(movie)
            }
        } else {
            if (sharedPreferences.getMoviesFilterDate(context) == null) {
                getFilter(query, isForIdiom)
            } else {
                val movie = sharedPreferences.getMoviesFilterDateFormat(context)
                setInfoFilter(movie)
            }
        }
    }

    private fun setInfoFilter(movie: MovieModel?) {
        movie?.let { _successFilter.postValue(it) }
        if (movie == null) _errorFilter.postValue(true)
    }

    private fun getFilter(query:String, isForIdiom: Boolean) {
        viewModelScope.launch {
            val call = getRetrofit().create(APIServiceInterface::class.java).getMovieByTrend(query)
            val movies = call.body()
            run {
                if (call.isSuccessful) {
                    movies?.let {
                        _successFilter.postValue(it)
                        if (isForIdiom) {
                            saveDataFilterIdiom(it)
                        } else {
                            saveDataFilterDate(it)
                        }
                    }
                } else {
                    _errorFilter.postValue(true)
                }
            }

        }
    }

    private fun saveDataTrend(movie: MovieModel) {
        sharedPreferences.saveMoviesTrend(context, movie)
    }

    private fun saveDataNextReleases(movie: MovieModel) {
        sharedPreferences.saveMoviesNextReleases(context, movie)
    }

    private fun saveDataFilterIdiom(movie: MovieModel) {
        sharedPreferences.saveMoviesFilterIdiom(context, movie)
    }

    private fun saveDataFilterDate(movie: MovieModel) {
        sharedPreferences.saveMoviesFilterDate(context, movie)
    }

    fun finisErrors() {
        _errorTrend.postValue(false)
        _errorNextReleases.postValue(false)
        _errorFilter.postValue(false)
    }
}