package com.backyardigans.doggiecare.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.backyardigans.doggiecare.R

class LoginActivity : AppCompatActivity() {
    lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.loginButton)



        loginButton.setOnClickListener {
            val intent = Intent(this , FeedActivity::class.java)
            startActivity(intent)
        }


    }



}