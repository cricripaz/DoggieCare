package com.backyardigans.doggiecare.data.userProfile.network

import com.backyardigans.doggiecare.Model.Profile
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileAPI {


    @GET("/posts")
    suspend fun getProfile(): Profile

    @POST("/posts/1928398")
    suspend fun updateProfile(): Profile


}