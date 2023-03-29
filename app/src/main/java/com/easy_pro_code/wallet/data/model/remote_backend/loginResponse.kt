package com.easy_pro_code.wallet.data.model.remote_backend

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class LoginResponse(

	@field:SerializedName("Phone")
	val phone: String? = null,

	@field:SerializedName("Token")
	val token: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("userName")
	val userName: String? = null,

	@field:SerializedName("Status")
	val status:Int? = null,

	@field:SerializedName("Balance")
	val balance: Int? = null,
	@field:SerializedName("message")
	val message: String? = null
) : Parcelable
