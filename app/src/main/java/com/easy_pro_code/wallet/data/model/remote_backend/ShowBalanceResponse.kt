package com.easy_pro_code.wallet.data.model.remote_backend

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ShowBalanceResponse(

	@field:SerializedName("Balance")
	val balance: Int? = null
) : Parcelable
