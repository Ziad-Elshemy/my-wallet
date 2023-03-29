package com.easy_pro_code.wallet

import android.app.Application
import com.easy_pro_code.wallet.data.model.remote_backend.SessionManager
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AuthUtils.manager= SessionManager.getInstance(applicationContext)
    }
}