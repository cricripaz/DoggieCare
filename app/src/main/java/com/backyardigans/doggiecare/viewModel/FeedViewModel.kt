package com.backyardigans.doggiecare.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.data.feed.FeedRepository
import com.backyardigans.doggiecare.data.feed.network.FeedNetworkControllerImp
import com.backyardigans.doggiecare.data.feed.persistency.FeedPersistencyControllerImp
import com.backyardigans.doggiecare.data.feedProfile.FeedRepositoryProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FeedViewModel : ViewModel() {


    val feedRepository = FeedRepository(FeedNetworkControllerImp(), FeedPersistencyControllerImp())
    val feedRepositoryProfile = FeedRepositoryProfile(FeedNetworkControllerImp(), FeedPersistencyControllerImp())


    val feedList = MutableLiveData<List<Feed>>()
    val feedListProfile = MutableLiveData<List<Feed>>()


    fun updatePost ( ){
        feedRepository.getAllPost().onEach {
            feedList.postValue(it)
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun updatePostProfile ( ){
        feedRepositoryProfile.getAllPost().onEach {
            feedList.postValue(it)
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}