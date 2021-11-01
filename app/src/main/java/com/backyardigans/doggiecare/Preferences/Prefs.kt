package com.backyardigans.doggiecare.Preferences

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore

class Prefs(val context: Context) {
    private val db = FirebaseFirestore.getInstance()
    val SHARED_NAME = "auth"
    val SHARED_EMAIL = "email"
    val SHARED_USER = "user"
    val SHARED_BIO = "bio"
//TODO separar gmail
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveEmail(email:String){
        storage.edit().putString(SHARED_EMAIL, email).apply()
    }

    fun getEmail(): String{
        return storage.getString(SHARED_EMAIL, "")!!
    }

    fun saveUser(user:String){
        storage.edit().putString(SHARED_USER, user).apply()
    }

    fun getUser(): String{
        var defaultNick = "default"
        db.collection("users").document(getEmail()).get().addOnSuccessListener {
            if (it.exists()) {
                defaultNick = it.data?.get("userNick") as String
                saveUser(defaultNick)
            } else {
                //TODO podriamos mandarlos al login
            }
        }
        return storage.getString(SHARED_USER, defaultNick)!!
    }

    fun saveBio(bio:String){
        storage.edit().putString(SHARED_BIO, bio).apply()
    }

    fun getBio(): String{
        var defaultBio = "default"
        return storage.getString(SHARED_BIO, defaultBio)!!
    }

    fun erase(){
        storage.edit().clear().apply()
    }
}