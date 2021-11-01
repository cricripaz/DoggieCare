package com.backyardigans.doggiecare.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backyardigans.doggiecare.Model.Profile
import com.backyardigans.doggiecare.data.userProfile.ProfileRepository
import com.backyardigans.doggiecare.data.userProfile.network.ProfileNetworkControllerImp
import com.backyardigans.doggiecare.data.userProfile.persistency.ProfilePersistencyControllerImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProfileViewModel:ViewModel() {
    private val profileRepository = ProfileRepository(ProfileNetworkControllerImp(), ProfilePersistencyControllerImp())
    val userProfile = MutableLiveData<Profile>()
    fun updateProfile ( ){
        profileRepository.getProfile().onEach {
            userProfile.postValue(it)
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}