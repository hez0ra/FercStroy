package com.example.fercstroy

class Request(val id: Int, val fio: String, val phone: String, val email: String, val type: String) {
    constructor(fio: String, phone: String, email:String, type:String) : this(-1, fio, phone, email, type)
}