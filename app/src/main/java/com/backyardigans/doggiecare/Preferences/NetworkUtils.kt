package com.backyardigans.doggiecare.Preferences

import android.content.Context

object NetworkUtils {
    var isOnline = false

    fun updateIsOnline(context: Context){
        isOnline = isNetworkConnected(context)
    }//TODO agregar return??
}