package com.easy_pro_code.wallet.data.model.remote_backend

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class TransactionResponse(

	@field:SerializedName("Sender")
	val sender: List<SenderItem?>? = null,

	@field:SerializedName("Receiver")
	val receiver: List<ReceiverItem?>? = null
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
data class ReceiverItem(

	@field:SerializedName("receiver")
	val receiver: String? = null,

	@field:SerializedName("transferTime")
	val transferTime: String? = null,

	@field:SerializedName("cashTransfer")
	val cashTransfer: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: UserId? = null
) : Parcelable

@Parcelize
data class SenderItem(

	@field:SerializedName("receiver")
	val receiver: String? = null,

	@field:SerializedName("transferTime")
	val transferTime: String? = null,

	@field:SerializedName("cashTransfer")
	val cashTransfer: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
) : Parcelable
