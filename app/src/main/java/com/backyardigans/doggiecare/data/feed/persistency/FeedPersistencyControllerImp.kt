package com.backyardigans.doggiecare.data.feed.persistency

import com.backyardigans.doggiecare.Model.Feed

class FeedPersistencyControllerImp : FeedPersistencyController {
    private var postList: List<Feed> = listOf()

    override fun getAllPosts(): List<Feed> {
        return postList
    }

    override fun savePosts(posts: List<Feed>) {
        postList = posts
    }
}