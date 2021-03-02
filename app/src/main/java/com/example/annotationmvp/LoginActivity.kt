package com.example.annotationmvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.annotationmvp.bean.LoginBean
import com.example.annotationmvp.contract.LoginContract
import com.example.annotationmvp.presenter.presenterImpl.LoginPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), LoginContract.LoginView<LoginContract.LoginPresenter> {
    var loginPresenter: LoginContract.LoginPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoginPresenterImpl(this)

        text_v.setOnClickListener {
            var map =HashMap<String,Any>()
                map["phone"]=18536088117
                map["key"]="ea0487d116037a807113e157f7b36df9"
            loginPresenter?.login("http://apis.juhe.cn", "/mobile/get",map) }
    }

    override fun setPresenter(t: LoginContract.LoginPresenter) {
        loginPresenter = t
    }

    override fun upData(loginBean: LoginBean) {
        text_v.text = loginBean.result.city
    }

    override fun onError(string: String?) {
        text_v.text = string
    }


}