package com.easy_pro_code.wallet.data.api.web_services

import com.easy_pro_code.wallet.data.model.remote_backend.TransferRequest
import com.easy_pro_code.wallet.data.model.remote_backend.TransferResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TransferBalanceWebService {
    @POST("user/transferCash")
    @Headers("Authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    suspend fun  transferBalance(@Body transferRequest: TransferRequest):TransferResponse
}