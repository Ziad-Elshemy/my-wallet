package com.easy_pro_code.wallet.data.api.web_services

import com.easy_pro_code.wallet.data.model.remote_backend.TransactionRequest
import com.easy_pro_code.wallet.data.model.remote_backend.TransactionResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransactionsWebServices {

    @POST("user/transferHistory")
    @Headers("Authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    suspend fun getTransactionHistory(@Body transaction:TransactionRequest):TransactionResponse
}