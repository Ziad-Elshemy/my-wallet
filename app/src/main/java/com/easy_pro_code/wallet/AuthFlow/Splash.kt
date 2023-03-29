package com.easy_pro_code.wallet.AuthFlow

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.easy_pro_code.wallet.AuthFlow.Presentation.model.LoginViewModel
import com.easy_pro_code.wallet.HomeFlow.Presentation.HomeActivity
import com.easy_pro_code.wallet.MainActivity
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding

    private val loginViewModel: LoginViewModel by viewModels()

    var pass:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =DataBindingUtil.setContentView(this,R.layout.activity_splash)

        // Handler().postDelayed({
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,MainActivity()::class.java)
            startActivity(intent)
            finish()
        }, 3000)// 3000 is the delayed time in milliseconds.
    }

    private fun Start(): Activity
    {
        with(loginViewModel)
        {
            val user = autoSignIn()

            user.token?.let {
                sessionManager.isTokenExpired()?.let {
                    if (it) {
                        loginViewModel.sessionManager.getPhone()?.let { phoneNumber ->
                            loginViewModel.logIn(phoneNumber,pass.toString())
                        }
                    }
                }
            }
            Log.i("tokennnnnnnnn",sessionManager.getToken().toString() )
            if(sessionManager.getToken()!=null)
            {
                return HomeActivity()
            }
            else{
                return MainActivity()
            }

        }


    }

}