package com.backyardigans.doggiecare.Preferences

import android.content.Context

class Prefs(val context: Context) {
    val SHARED_NAME = "auth"
    val SHARED_EMAIL = "email"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveEmail(email:String){
        storage.edit().putString(SHARED_EMAIL, email).apply()
    }

    fun getEmail(): String{
        return storage.getString(SHARED_EMAIL, "")!!
    }


    fun erase(){

        storage.edit().clear().apply()


    }
}