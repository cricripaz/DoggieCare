package com.backyardigans.doggiecare.data.feedProfile.network

import com.backyardigans.doggiecare.Model.Feed
import retrofit2.http.GET
import retrofit2.http.POST

interface FeedAPI {


    @GET("/posts")
    suspend fun getAllPosts(): List<Feed>

    @POST("/posts/1928398")
    suspend fun addNewPost(): List<Feed>


}