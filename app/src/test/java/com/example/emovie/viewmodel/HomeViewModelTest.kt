package com.example.emovie.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.emovie.model.MovieModel
import com.example.emovie.utilities.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.*
import org.junit.Assert.*
import org.junit.rules.TestRule

class HomeViewModelTest {

    private var aplicacion: Application? = null
    private var viewmodel: HomeViewModel? =null
    private val movie = MovieModel(
        page = 1,
        results = listOf(),
        total_pages = 1,
        total_results = 1
    )

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @After
    fun afterTests() {
        aplicacion = null
        viewmodel = null

    }

    @Before
    fun setUp() {
        aplicacion = mockk(relaxUnitFun = true)
        viewmodel = HomeViewModel(aplicacion!!)
        mockkObject(SharedPreferences)

        //Get
        every { SharedPreferences.getMoviesFilterIdiom(aplicacion!!) }.returns("si")
        every { SharedPreferences.getMoviesFilterDate(aplicacion!!) }.returns("si")
        //GetDataJson
        every { SharedPreferences.getMoviesFilterDateFormat(aplicacion!!) }.returns(movie)
        every { SharedPreferences.getMoviesFilterIdiomFormat(aplicacion!!) }.returns(movie)
    }

    @Test
    fun searchByFilterIdiom() {
        viewmodel?.searchByFilter("", true)
        assertNotNull(viewmodel?.successFilter?.value)
    }

    @Test
    fun searchByFilterDate() {
        viewmodel?.searchByFilter("", false)
        assertNotNull(viewmodel?.successFilter?.value)
    }

    @Test
    fun finisErrors() {
        viewmodel?.finisErrors()
        viewmodel?.errorTrend?.value?.let { assertFalse(it) }
        viewmodel?.errorNextReleases?.value?.let { assertFalse(it) }
        viewmodel?.errorFilter?.value?.let { assertFalse(it) }
    }
}