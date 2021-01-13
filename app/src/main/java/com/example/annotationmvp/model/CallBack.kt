package com.example.annotationmvp.model

interface CallBack <T>{
    fun onSuccess(t: T)
    fun onError(str: String?)
    fun onData(str: String?)
}