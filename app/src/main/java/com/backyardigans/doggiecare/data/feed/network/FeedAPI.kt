package com.backyardigans.doggiecare.data.feed.network

import com.backyardigans.doggiecare.Model.Feed
import retrofit2.http.GET
import retrofit2.http.POST

interface FeedAPI {

    suspend fun getAllPosts(): List<Feed>

}