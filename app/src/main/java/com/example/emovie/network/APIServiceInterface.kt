package com.example.emovie.network

import com.example.emovie.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIServiceInterface {
    @GET
    suspend fun getMovieByTrend(@Url url: String): Response<MovieModel>
}