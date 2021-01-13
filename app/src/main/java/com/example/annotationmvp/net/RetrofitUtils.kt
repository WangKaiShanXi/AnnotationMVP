package com.mlxcchina.utilslibrary.utils.net

import com.example.annotationmvp.model.CallBack
import com.example.annotationmvp.net.ApiService
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.IOException
import java.lang.reflect.ParameterizedType

//提供单例模式，返回retrofit对象
class RetrofitUtils private constructor() {
    fun <T> getHttp(baseUrl: String, url: String, callBack: CallBack<T>) {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val observable = apiService.getHttp(url)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    callBack.onError(e.message)
                }

                override fun onNext(s: ResponseBody) {
                    val types = callBack.javaClass.genericInterfaces
                    val ty = (types[0] as ParameterizedType).actualTypeArguments
                    val t = ty[0]
                    val gson = Gson()
                    var gson_FT: T? = null
                    try {
                        val data = s.string()
                        gson_FT = gson.fromJson<T>(data, t)
                        callBack.onSuccess(gson_FT)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }


                }
            })

    }


    fun <T> getHttp(
        baseUrl: String,
        url: String,
        parameters: Map<String, Any>,
        callBack: CallBack<T>
    ) {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val observable = apiService.getHttp(url, parameters)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ResponseBody> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {
                    callBack.onError(e.message)

                }

                override fun onNext(s: ResponseBody) {
                    val types = callBack.javaClass.genericInterfaces
                    val ty = (types[0] as ParameterizedType).actualTypeArguments
                    val t = ty[0]
                    val gson = Gson()
                    var gson_FT: T? = null
                    try {
                        val data = s.string()
                        callBack.onData(data)
                        gson_FT = gson.fromJson<T>(data, t)
                        callBack.onSuccess(gson_FT)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }


                }
            })

    }


    fun <T> postHttp(BaseUrl: String, url: String, CallBack: CallBack<T>) {


        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
       var observable = apiService.postHttps<Any>(url)
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

            .subscribe(object : Observer<ResponseBody> {
                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {

                }

                override fun onNext(responseBody: ResponseBody) {
                    try {
                        val data = responseBody.string()
                        val types = CallBack.javaClass.genericInterfaces
                        val ty = (types[0] as ParameterizedType).actualTypeArguments
                        val t = ty[0]
                        val gson = Gson()
                        var gson_FT: T? = null
                        gson_FT = gson.fromJson<T>(data, t)
                        CallBack.onSuccess(gson_FT)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            })


    }


    companion object {

        @Volatile
        private var retorfitUtils: RetrofitUtils? = null

        val instance: RetrofitUtils?
            get() {
                if (null == retorfitUtils) {
                    synchronized(RetrofitUtils::class.java) {
                        if (null == retorfitUtils) {
                            retorfitUtils = RetrofitUtils()
                        }
                    }
                }
                return retorfitUtils
            }
    }


}