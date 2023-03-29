package com.easy_pro_code.wallet.data.api.web_services

import com.easy_pro_code.wallet.data.model.remote_backend.DepositRequest
import com.easy_pro_code.wallet.data.model.remote_backend.DepositResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DepositWebService {
    @Headers("authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    @POST("user/tellerDeposit")
    suspend fun deposit(@Body depositRequest: DepositRequest):DepositResponse
}