package com.example.annotationmvp.bean

class LoginBean(
    var resultcode: String,
    var reason: String,
    var result: Result,
    var errorCode: Int
) {

}

class Result {
    var province: String? = null
    var city: String? = null
    var areacode: String? = null
    var zip: String? = null
    var company: String? = null
    var card: String? = null

}