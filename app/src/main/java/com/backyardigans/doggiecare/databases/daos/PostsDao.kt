package com.backyardigans.doggiecare.databases.daos

import com.backyardigans.doggiecare.Model.Feed
import kotlinx.coroutines.flow.Flow

interface PostsDao {
    fun getAllPosts(): Flow<List<Feed>>
    fun savePost(posts: Feed)
}