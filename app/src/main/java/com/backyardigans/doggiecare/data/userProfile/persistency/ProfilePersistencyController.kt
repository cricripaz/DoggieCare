package com.backyardigans.doggiecare.data.userProfile.persistency

import com.backyardigans.doggiecare.Model.Profile

interface ProfilePersistencyController {
    fun getProfile(): Profile
    fun saveProfile(posts: Profile)
}