package com.easy_pro_code.wallet.data.api.web_services

import com.easy_pro_code.wallet.data.model.remote_backend.ShowBalanceRequest
import com.easy_pro_code.wallet.data.model.remote_backend.ShowBalanceResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ShowBalanceWebService {
    @Headers("Authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    @POST("getBalnce")
    suspend fun showBalance(@Body User: ShowBalanceRequest):ShowBalanceResponse
}