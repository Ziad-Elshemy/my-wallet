package com.easy_pro_code.wallet.data.model.remote_backend

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignUpRequest(

	@field:SerializedName("Phone")
	val phone: String? = null,

	@field:SerializedName("userName")
	val userName: String? = null,

	@field:SerializedName("Password")
	val password: String? = null
) : Parcelable
