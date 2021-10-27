package com.backyardigans.doggiecare.Preferences

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class App: Application() {
    companion object{//TODO necesitamos daos??
        lateinit var database:FirebaseFirestore
    }

    override fun onCreate() {
        super.onCreate()
        database = Firebase.firestore
    }
}