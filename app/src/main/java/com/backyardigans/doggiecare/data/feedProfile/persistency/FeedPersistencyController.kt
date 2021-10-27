package com.backyardigans.doggiecare.data.feedProfile.persistency

import com.backyardigans.doggiecare.Model.Feed

interface FeedPersistencyController {
    fun getAllPosts(): List<Feed>
    fun savePosts(posts: List<Feed>)
}