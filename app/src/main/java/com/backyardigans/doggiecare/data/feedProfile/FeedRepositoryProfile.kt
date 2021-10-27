package com.backyardigans.doggiecare.data.feedProfile

import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.data.feed.network.FeedNetworkController
import com.backyardigans.doggiecare.data.feed.persistency.FeedPersistencyController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FeedRepositoryProfile (val network : FeedNetworkController, val persistency : FeedPersistencyController){



    fun getAllPost() : Flow<List<Feed>> {
        return flow {
            emit(network.getAllPosts())
        }
    }



}