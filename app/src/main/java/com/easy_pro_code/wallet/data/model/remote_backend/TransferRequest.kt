package com.easy_pro_code.wallet.data.model.remote_backend

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class TransferRequest(

	@field:SerializedName("receiver")
	val receiver: String? = null,

	@field:SerializedName("cashTransfer")
	val cashTransfer: Int? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("Password")
	val password: String? = null
) : Parcelable
