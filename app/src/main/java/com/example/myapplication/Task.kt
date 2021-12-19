package com.example.myapplication

class Task {

    var id: Int = 0
    var name: String = ""
    var details: String = ""

    constructor(name: String, details: String) {
        this.name = name
        this.details = details
    }

    constructor() {}
}