package com.backyardigans.doggiecare.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backyardigans.doggiecare.Model.Profile
import com.backyardigans.doggiecare.Preferences.Prefs
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.google.firebase.firestore.FirebaseFirestore

class ProfileViewModel:ViewModel() {
    val db = FirebaseFirestore.getInstance()
    val profileModel = MutableLiveData<Profile>(Profile(prefs.getUser(), prefs.getBio()))


    fun actualizar(profile:Profile){


        if (profile.bio != null) {
            prefs.saveBio(profile.bio)
        }
        if (profile.nickName != null) {
            prefs.saveUser(profile.nickName)
        }

        profileModel.postValue(Profile(prefs.getUser(), prefs.getBio()))
    }
}