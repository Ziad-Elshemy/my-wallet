package com.easy_pro_code.wallet.data.model.remote_firebase

import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseUtils {
    val auth = Firebase.auth
    lateinit var token: PhoneAuthProvider.ForceResendingToken
}