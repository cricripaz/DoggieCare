package com.backyardigans.doggiecare.databases

import com.backyardigans.doggiecare.databases.daos.PostsDao

abstract class AppDatabase {
    abstract fun postDao(): PostsDao
}