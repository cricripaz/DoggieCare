package com.backyardigans.doggiecare.data.feed.network

import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.Preferences.App.Companion.database
import kotlinx.coroutines.tasks.await

class FeedNetworkControllerImp : FeedNetworkController {

    val db = database
    val response = db.collection("publicaciones").get()


    override suspend fun getAllPosts(): List<Feed> {

        return response.await().toObjects(Feed::class.java)
    }
}