package com.easy_pro_code.wallet.data.api.web_services

import com.easy_pro_code.wallet.data.model.remote_backend.WithdrawRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface WithdrawWebService {
    @Headers("authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    @POST("user/withdraw")
    suspend fun withdraw(@Body withdraw:WithdrawRequest):Response<*>
}