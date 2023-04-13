package com.easy_pro_code.wallet.data.api.web_services

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransferHistoryWebService {
    @POST("user/transferHistory")
    @Headers("Authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    suspend fun getTransactionHistory()
}