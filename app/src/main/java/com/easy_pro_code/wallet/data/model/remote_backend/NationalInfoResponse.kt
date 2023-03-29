package com.easy_pro_code.wallet.data.model.remote_backend

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class NationalInfoResponse(

	@field:SerializedName("date")
	val date: String? = null,
	@field:SerializedName("message")
	val message: String? = null
) : Parcelable
