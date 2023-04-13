package com.easy_pro_code.wallet.data.model.remote_backend

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class WithdrawRequest(

	@field:SerializedName("Money")
	val money: Int? = null,

	@field:SerializedName("Phone")
	val phone: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("Password")
	val password: String? = null,

	@field:SerializedName("Name")
	val name: String? = null
) : Parcelable
