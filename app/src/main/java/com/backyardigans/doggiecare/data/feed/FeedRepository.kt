package com.backyardigans.doggiecare.data.feed

import android.util.Log
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.data.feed.network.FeedNetworkController
import com.backyardigans.doggiecare.data.feed.persistency.FeedPersistencyController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FeedRepository(
    private val network: FeedNetworkController,
    private val persistence: FeedPersistencyController
) {
     fun getAllPostList(): Flow<List<Feed>> {
        return persistence.getAllPosts()
    }

    fun updatePosts(): Flow<Any> {
        return flow {
            try {
                val posts = network.getAllPosts()
               persistence.savePosts(posts)
                emit(posts)
            } catch (e: Exception) {
                Log.e("ERROR", e.message!!)
            }
        }
    }
}