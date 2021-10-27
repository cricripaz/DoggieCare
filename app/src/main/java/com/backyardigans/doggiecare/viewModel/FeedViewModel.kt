package com.backyardigans.doggiecare.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.data.feed.FeedRepository
import com.backyardigans.doggiecare.data.feed.network.FeedNetworkControllerImp
import com.backyardigans.doggiecare.data.feed.persistency.FeedPersistencyControllerImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn

class FeedViewModel:ViewModel() {
    private val feedRepository =
        FeedRepository(FeedNetworkControllerImp(), FeedPersistencyControllerImp())

    val posts: LiveData<List<Feed>> = feedRepository.getAllPostList().asLiveData(Dispatchers.IO)

    fun updateFeed(): Job {
        return feedRepository.updatePosts().launchIn(CoroutineScope(Dispatchers.IO))
    }
}