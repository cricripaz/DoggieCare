package com.backyardigans.doggiecare.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backyardigans.doggiecare.Model.Chat

class ChatViewModel:ViewModel() {

    val chatModel = MutableLiveData<Chat>(Chat("Default","Default",0))
    fun updateChat( chat : Chat ){
        val currentData = chat
        chatModel.postValue(currentData)
    }

}