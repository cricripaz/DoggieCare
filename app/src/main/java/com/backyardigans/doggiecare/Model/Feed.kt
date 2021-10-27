package com.backyardigans.doggiecare.Model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.DrawableRes
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot

data class Feed(@DrawableRes val img:Int?, val userNick: String?, val animalAge: String?,
                val animalName: String?, val animalBreed: String?, val description: String?
)




