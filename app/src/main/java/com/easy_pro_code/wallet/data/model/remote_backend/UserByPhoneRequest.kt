package com.easy_pro_code.wallet.data.model.remote_backend

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserByPhoneRequest(

	@field:SerializedName("Phone")
	val phone: String? = null
) : Parcelable
