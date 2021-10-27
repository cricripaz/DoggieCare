package com.backyardigans.doggiecare.Model

import androidx.annotation.DrawableRes

class Feed(){

    @DrawableRes  var  img:Int = 0
    var userName:String = ""
    var nickName:String =""
    var AnimalName:String=""
    var AnimalAge:String=""


    constructor( img:Int, userName:String, nickName:String, AnimalName:String, AnimalAge:String):this() {

        this.img = img
        this.userName = userName
        this.nickName = nickName
        this.AnimalAge = AnimalAge
        this.AnimalName = AnimalName


    }
}



