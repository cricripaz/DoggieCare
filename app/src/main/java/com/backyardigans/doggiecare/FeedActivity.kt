package com.backyardigans.doggiecare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.databinding.ActivityFeedBinding
import com.backyardigans.doggiecare.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class FeedActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    private val homeFragment = HomeFragment()
    private val addFragment = AddFragment()
    private val searchFragment = SearchFragment()
    private val chatFragment = ChatFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.itmProfile -> {
                    replaceFragment(R.id.container, profileFragment)
                }
                R.id.itmHome -> {
                    replaceFragment(R.id.container, homeFragment)
                }
                R.id.itmChat -> {
                    replaceFragment(R.id.container, chatFragment)
                }
                R.id.itmAdd -> {
                    replaceFragment(R.id.container, addFragment)
                }
                R.id.itmSearch -> {
                    replaceFragment(R.id.container, searchFragment)
                }
            }
            true
        }

        bottomNavigationView.selectedItemId = R.id.itmHome
    }
}