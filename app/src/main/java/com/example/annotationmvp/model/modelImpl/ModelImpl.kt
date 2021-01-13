package com.example.annotationmvp.model.modelImpl

import com.example.annotationmvp.model.BaseModel
import com.example.annotationmvp.model.CallBack
import com.mlxcchina.utilslibrary.utils.net.RetrofitUtils

class ModelImpl : BaseModel {
    fun <T> getHttp(
        baseUrl: String, url: String, callBack: CallBack<T>
    ) {
        RetrofitUtils.instance!!.getHttp(baseUrl, url, callBack)
    }

    fun <T> getHttp(
        baseUrl: String,
        url: String,
        parameters: Map<String, Any>,
        callBack: CallBack<T>
    ) {
        RetrofitUtils.instance!!.getHttp(baseUrl, url, parameters, callBack)
    }

    fun <T> postHttp(
        baseUrl: String,
        url: String,
        parameters: Map<String, String>,
        callBack: CallBack<T>
    ) {
        RetrofitUtils.instance!!.getHttp(baseUrl, url, parameters, callBack)
    }
}