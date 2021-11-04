package com.backyardigans.doggiecare.data.userProfile.network

import com.backyardigans.doggiecare.Model.Profile

interface ProfileNetworkController {
    suspend fun getProfile(): Profile
    suspend fun getPhotoProfile(userMail:String): Profile

}