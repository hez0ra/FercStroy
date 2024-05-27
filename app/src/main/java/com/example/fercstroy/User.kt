package com.example.fercstroy


class User(val fio: String, val phone: String, val email: String, val pass: String, val userID: Int, val image: ByteArray?) {

    constructor(userName: String, phone: String, email: String, pass: String): this(userName, phone, email, pass, -1, null)
}