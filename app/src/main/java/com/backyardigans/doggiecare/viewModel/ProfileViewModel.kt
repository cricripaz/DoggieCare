package com.backyardigans.doggiecare.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backyardigans.doggiecare.Model.Profile

class ProfileViewModel:ViewModel() {
    val profileModel = MutableLiveData<Profile>()

    fun actualizar(profile:Profile){
        val currentData = profile
        profileModel.postValue(currentData)
    }
}