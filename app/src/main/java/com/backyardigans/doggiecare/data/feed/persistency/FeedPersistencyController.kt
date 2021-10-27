package com.backyardigans.doggiecare.data.feed.persistency

import com.backyardigans.doggiecare.Model.Feed
import kotlinx.coroutines.flow.Flow

interface FeedPersistencyController {
    fun getAllPosts(): Flow<List<Feed>>
    fun savePosts(posts: List<Feed>)
}