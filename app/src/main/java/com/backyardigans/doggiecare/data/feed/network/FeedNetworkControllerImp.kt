package com.backyardigans.doggiecare.data.feed.network

import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FeedNetworkControllerImp : FeedNetworkController {

    val db = Firebase.firestore


    override suspend fun getAllPosts(): List<Feed> {
      val response = db.collection("publicaciones").orderBy(
          "created",Query.Direction.DESCENDING).get().await()
        return response.toObjects(Feed::class.java)
    }

    override suspend fun getAllPostsProfile(): List<Feed> {
        val tempt = db.collection("publicaciones")
            .whereEqualTo("userMail", prefs.getEmail())
            .orderBy("created", Query.Direction.DESCENDING)


        val response =tempt.get().await()
        return response.toObjects(Feed::class.java)
    }

}


