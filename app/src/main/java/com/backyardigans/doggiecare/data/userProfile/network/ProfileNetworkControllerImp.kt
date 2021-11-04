package com.backyardigans.doggiecare.data.userProfile.network

import com.backyardigans.doggiecare.Model.Profile
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ProfileNetworkControllerImp : ProfileNetworkController {

    val db = Firebase.firestore

    override suspend fun getProfile(): Profile {
      val response = db.collection("users").document(prefs.getEmail()).get().await()

        return response.toObject(Profile::class.java)!!
    }

    override suspend fun getPhotoProfile(userMail:String): Profile {
        val response = db.collection("users").document(userMail).get().await()

        return response.toObject(Profile::class.java)!!
    }


}


