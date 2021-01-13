package com.example.annotationmvp.contract

import com.example.annotationmvp.bean.LoginBean
import com.example.annotationmvp.presenter.basePresenter.BasePresenter
import com.example.annotationmvp.view.baseView.BaseView

interface LoginContract {
    interface LoginPresenter : BasePresenter {
        fun login(baseUrl: String,
                  url: String,
                  parameters: Map<String, Any>)
    }

    interface LoginView<LoginPresenter> : BaseView<LoginPresenter> {
        fun upData(loginBean: LoginBean)
    }
}