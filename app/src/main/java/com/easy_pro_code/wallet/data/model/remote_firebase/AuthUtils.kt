package com.easy_pro_code.wallet.data.model.remote_firebase

import com.easy_pro_code.wallet.data.model.remote_backend.SessionManager

object AuthUtils {
    lateinit var manager: SessionManager
    var chatSdkFlag:Boolean?=null

    var networkFlag=false
}