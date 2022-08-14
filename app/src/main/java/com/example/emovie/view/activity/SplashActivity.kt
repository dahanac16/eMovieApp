package com.example.emovie.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.emovie.databinding.ActivitySplashBinding


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private var show = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageSplash.alpha = 0f
        binding.imageSplash.animate().setDuration(2000).alpha(1f).withEndAction{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onResume() {
        if (!show) {
            finish()
        }
        super.onResume()
    }

    override fun onPause() {
        show = false
        super.onPause()
    }
}