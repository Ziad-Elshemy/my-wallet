package com.easy_pro_code.wallet.data.model.remote_backend

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class NationalInfoRequest(

	@field:SerializedName("NationalIdImg")
	val nationalIdImg: String? = null,

	@field:SerializedName("NationalId")
	val nationalId: String? = null,

	@field:SerializedName("profileImg")
	val profileImg: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
) : Parcelable
