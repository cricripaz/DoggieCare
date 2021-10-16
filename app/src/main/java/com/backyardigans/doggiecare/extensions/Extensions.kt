package com.backyardigans.doggiecare

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

    fun AppCompatActivity.addFragmentToStack(@IdRes containerID: Int, fragment: Fragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.add(containerID, fragment)
        ft.addToBackStack("Test")
        ft.commit()
    }

    fun AppCompatActivity.replaceFragment(@IdRes containerID: Int, fragment: Fragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(containerID, fragment)
        ft.commit()
    }