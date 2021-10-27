package com.backyardigans.doggiecare.Model



class Profile() {
    var userNick: String = ""
    var userBio: String = ""


    constructor(userNick: String, userBio: String) : this() {

        this.userNick = userNick
        this.userBio = userBio
    }
}