package com.backyardigans.doggiecare.Model
class Profile() {
    var userNick: String = ""
    var userBio: String = ""
    var userPic: String = ""

    constructor(userNick: String, userBio: String, userPic: String) : this() {
        this.userNick = userNick
        this.userBio = userBio
        this.userPic = userPic
    }
}