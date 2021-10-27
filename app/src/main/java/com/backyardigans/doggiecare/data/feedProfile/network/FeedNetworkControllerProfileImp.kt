package com.backyardigans.doggiecare.data.feedProfile.network

import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.data.feed.persistency.FeedPersistencyController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedNetworkControllerProfileImp : FeedNetworkController {

    val db = Firebase.firestore

    override suspend fun getAllPosts(): List<Feed> {
      val response = db.collection("publicaciones").whereEqualTo("userMail",prefs.getEmail()).get().await()
        return response.toObjects(Feed::class.java)
    }


}


