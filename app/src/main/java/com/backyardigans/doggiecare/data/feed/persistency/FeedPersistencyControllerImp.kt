package com.backyardigans.doggiecare.data.feed.persistency

import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.Preferences.App
import com.backyardigans.doggiecare.Preferences.App.Companion.database
import com.backyardigans.doggiecare.data.TemptDataSource
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class FeedPersistencyControllerImp : FeedPersistencyController {
    private lateinit var postList:Flow<List<Feed>>



    override fun getAllPosts(): Flow<List<Feed>> {
        return postList
    }

    override fun savePosts(posts: List<Feed>) {
        postList = listOf(posts).asFlow()
    }
}