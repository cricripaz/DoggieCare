package com.backyardigans.doggiecare.data.userProfile

import com.backyardigans.doggiecare.Model.Profile
import com.backyardigans.doggiecare.data.userProfile.network.ProfileNetworkController
import com.backyardigans.doggiecare.data.userProfile.persistency.ProfilePersistencyController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ProfileRepository (val network : ProfileNetworkController, val persistency : ProfilePersistencyController){



    fun getProfile() : Flow<Profile> {
        return flow {
            emit(network.getProfile())
        }
    }
    fun getPhotoProfile(userMail:String) : Flow<Profile> {
        return flow {
            emit(network.getPhotoProfile(userMail))
        }
    }


}