package com.backyardigans.doggiecare.Model
class Feed(){

    var  urlImage:String = ""
    var userNick:String = ""
    var userMail:String = ""
    var AnimalName:String=""
    var AnimalAge:String=""
    var AnimalBreed:String=""
    var description:String=""

    constructor( img:String, userNick:String, userMail:String,  animalName:String, animalAge:String, animalBreed:String, description:String):this() {

        this.urlImage = img
        this.userNick = userNick
        this.userMail = userMail
        this.AnimalAge = animalAge
        this.AnimalName = animalName
        this.AnimalBreed = animalBreed
        this.description = description

    }
}



