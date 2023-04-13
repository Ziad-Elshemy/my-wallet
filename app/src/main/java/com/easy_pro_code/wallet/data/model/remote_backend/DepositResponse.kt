package com.easy_pro_code.wallet.data.model.remote_backend

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DepositResponse(

	@field:SerializedName("Deposit")
	val deposit: Deposit? = null
) : Parcelable

@Parcelize
data class UserId(

	@field:SerializedName("Status")
	val status: Int? = null,

	@field:SerializedName("Phone")
	val phone: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("userName")
	val userName: String? = null,

	@field:SerializedName("Balance")
	val balance: Int? = null,

	@field:SerializedName("Password")
	val password: String? = null
) : Parcelable

@Parcelize
data class Deposit(

	@field:SerializedName("Status")
	val status: Int? = null,

	@field:SerializedName("Money")
	val money: Int? = null,

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("Type")
	val type: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("Time")
	val time: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: UserId? = null
) : Parcelable
