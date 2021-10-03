package com.backyardigans.doggiecare.Model

import androidx.annotation.DrawableRes

data class Feed(@DrawableRes val img:Int, val userName:String, val nickName:String,
                val AnimalName:String, val AnimalAge:String)
