package com.easy_pro_code.wallet.data.model.remote_backend

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class TransferResponse(

	@field:SerializedName("Transfer")
	val transfer: Transfer? = null,

	@field:SerializedName("Mss")
	val message: String? = null
) : Parcelable

@Parcelize
data class Transfer(

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


