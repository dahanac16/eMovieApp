package com.example.emovie.viewmodel

import android.app.Application
import io.mockk.mockk
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {

    private var aplicacion: Application? = null
    private var viewmodel: DetailMovieViewModel? =null

    @After
    fun afterTests() {
        aplicacion = null
        viewmodel = null
    }

    @Before
    fun setUp() {
        aplicacion = mockk(relaxUnitFun = true)
        viewmodel = DetailMovieViewModel(aplicacion!!)
    }

    @Test
    fun getFormatUrlTest () {
        val compareUrl = "https://image.tmdb.org/t/p/w500/prueba.png"
        val url = viewmodel?.getFormatUrl("/prueba.png")
        Assert.assertEquals(url, compareUrl)
        Assert.assertNotNull(url)
        Assert.assertTrue( url != "")
    }
}