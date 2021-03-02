package com.example.annotationmvp.presenter.presenterImpl

import com.example.annotationmvp.bean.LoginBean
import com.example.annotationmvp.contract.LoginContract.LoginPresenter
import com.example.annotationmvp.contract.LoginContract.LoginView
import com.example.annotationmvp.model.CallBack
import com.example.annotationmvp.model.modelImpl.ModelImpl

class LoginPresenterImpl(var view: LoginView<LoginPresenter>) : LoginPresenter {
    var model: ModelImpl?=null
    init {
        model = ModelImpl()
        view.setPresenter(this)
    }

    override fun login(baseUrl: String, url: String, parameters: Map<String, Any>) {
        model?.getHttp(baseUrl,url,parameters,object : CallBack<LoginBean> {
            override fun onSuccess(loginBean: LoginBean) {
                view.upData(loginBean)
            }

            override fun onError(str: String?) {
                view.onError(str)
            }

            override fun onData(str: String?) {
            }

        })
    }


}