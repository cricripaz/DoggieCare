package com.backyardigans.doggiecare.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backyardigans.doggiecare.Model.Chat
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.data.feed.FeedRepository
import com.backyardigans.doggiecare.data.feed.network.FeedNetworkController
import com.backyardigans.doggiecare.data.feed.network.FeedNetworkControllerImp
import com.backyardigans.doggiecare.data.feed.persistency.FeedPersistencyControllerImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ChatViewModel:ViewModel() {




    val chatModel = MutableLiveData<Chat>(Chat("Default","Default",0))


    fun updateChat( chat : Chat ){

        val currentData = chat

        chatModel.postValue(currentData)

    }




}