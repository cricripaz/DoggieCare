package com.backyardigans.doggiecare.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.backyardigans.doggiecare.R

class SplashScreenActivity  : AppCompatActivity() {

    private var showMessage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}