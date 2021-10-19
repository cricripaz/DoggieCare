package com.backyardigans.doggiecare.Preferences

import android.content.Context

class Prefs(val context: Context) {
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
        return storage.getString(SHARED_USER, "deafault")!!
    }
    fun saveBio(bio:String){
        storage.edit().putString(SHARED_BIO, bio).apply()
    }

    fun getBio(): String{
        return storage.getString(SHARED_BIO, "default")!!
    }

    fun erase(){

        storage.edit().clear().apply()


    }
}