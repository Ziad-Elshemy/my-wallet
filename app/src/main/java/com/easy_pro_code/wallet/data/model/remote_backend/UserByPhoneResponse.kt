package com.easy_pro_code.wallet.data.model.remote_backend

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserByPhoneResponse(

	@field:SerializedName("message")
	val messages: String? = null,

	@field:SerializedName("Status")
	val status: Int? = null,

	@field:SerializedName("Phone")
	val phone: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("userName")
	val userName: String? = null,

	@field:SerializedName("Balance")
	val balance: Int? = null
) : Parcelable
