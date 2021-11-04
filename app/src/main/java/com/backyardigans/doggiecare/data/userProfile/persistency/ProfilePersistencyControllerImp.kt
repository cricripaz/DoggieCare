package com.backyardigans.doggiecare.data.userProfile.persistency

import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.Model.Profile

class ProfilePersistencyControllerImp : ProfilePersistencyController {
    private var profile: Profile = Profile("Default", "Default", "Default")

    override fun getProfile(): Profile {
        return profile
    }

    override fun saveProfile(profiles: Profile) {
        profile = profiles
    }
}