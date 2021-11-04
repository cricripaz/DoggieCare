package com.backyardigans.doggiecare.data.feed.network

import com.backyardigans.doggiecare.Model.Feed

interface FeedNetworkController {
    suspend fun getAllPosts(): List<Feed>
    suspend fun getAllPostsProfile(): List<Feed>
    suspend fun getAllPostsSearch(search:String): List<Feed>


}