package com.easy_pro_code.wallet.AuthFlow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.easy_pro_code.wallet.MainActivity
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding

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
}