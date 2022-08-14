package com.example.emovie.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.emovie.databinding.ActivityMainBinding
import com.example.emovie.view.fragments.HomeMovieFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        try {
            val fragment = getVisibleFragment()?.childFragmentManager!!.fragments[0]
            if (fragment is HomeMovieFragment) {
                finish()
            }
        } catch (e: Exception){  }
        super.onBackPressed()
    }

    private fun getVisibleFragment(): Fragment? {
        try {
            val fragmentManager = this@MainActivity.supportFragmentManager
            val fragments = fragmentManager.fragments
            for (fragment in fragments) {
                val f = fragment.childFragmentManager.fragments[0]
                if (f is HomeMovieFragment) {
                    return fragment
                }
            }
        } catch (e: Exception){  }
        return null
    }
}