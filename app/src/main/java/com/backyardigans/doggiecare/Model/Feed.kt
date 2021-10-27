package com.backyardigans.doggiecare.Model

import androidx.annotation.DrawableRes

class Feed(){

    @DrawableRes  var  img:Int = 0
    var userNick:String = ""
    var AnimalName:String=""
    var AnimalAge:String=""
    var AnimalBreed:String=""
    var description:String=""

    constructor( img:Int, userNick:String, animalName:String, animalAge:String, animalBreed:String, description:String):this() {

        this.img = img
        this.userNick = userNick
        this.AnimalAge = animalAge
        this.AnimalName = animalName
        this.AnimalBreed = animalBreed
        this.description = description

    }
}



