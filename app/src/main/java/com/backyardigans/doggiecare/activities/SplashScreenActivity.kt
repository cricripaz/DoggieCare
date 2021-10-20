package com.backyardigans.doggiecare.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.google.firebase.firestore.FirebaseFirestore

class SplashScreenActivity  : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()

    private var showMessage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide()

        Handler().postDelayed({
            if (prefs.getEmail().isNotEmpty()){

                db.collection("users").document(prefs.getEmail()).get().addOnSuccessListener {
                if (it.exists()){
                    val defaultBio = it.data?.get("userBio") as String
                    prefs.saveBio(defaultBio)


                }
            }
            db.collection("users").document(prefs.getEmail()).get().addOnSuccessListener {
                if (it.exists()){
                    val defaultBio = it.data?.get("userNick") as String
                    prefs.saveUser(defaultBio)


                }
            }
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}