package com.easy_pro_code.wallet.data.model.remote_firebase

import android.os.Parcelable
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhoneVerification(
    val verificationId: String,
    val token: PhoneAuthProvider.ForceResendingToken,
    val phoneNumber: String
) : Parcelable