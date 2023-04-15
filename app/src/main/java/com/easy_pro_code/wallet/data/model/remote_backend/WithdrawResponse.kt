package com.easy_pro_code.wallet.data.model.remote_backend

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WithdrawResponse(

	@field:SerializedName("Deposit")
	val withdraw: Withdraw? = null,

	@field:SerializedName("message")
	val messages: String? = null

) : Parcelable

@Parcelize
data class WithdrawUserId(

	@field:SerializedName("Status")
	val status: Int? = null,

	@field:SerializedName("Phone")
	val phone: String? = null,

	@field:SerializedName("__v")
	val V: Int? = null,

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
data class Withdraw(

	@field:SerializedName("Status")
	val status: Int? = null,

	@field:SerializedName("Money")
	val money: Int? = null,

	@field:SerializedName("Type")
	val type: Int? = null,

	@field:SerializedName("__v")
	val V: Int? = null,

	@field:SerializedName("Time")
	val time: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: WithdrawUserId? = null
) : Parcelable
