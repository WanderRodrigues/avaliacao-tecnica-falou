package com.falou.avaliacao_tecnica_falou.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.falou.avaliacao_tecnica_falou.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({ startSignInActivity() }, 3000)
    }

    private fun startSignInActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}