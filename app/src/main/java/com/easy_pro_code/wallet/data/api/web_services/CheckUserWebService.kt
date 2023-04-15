package com.easy_pro_code.wallet.data.api.web_services

import com.easy_pro_code.wallet.data.model.remote_backend.UserByPhoneRequest
import com.easy_pro_code.wallet.data.model.remote_backend.UserByPhoneResponse
import com.easy_pro_code.wallet.data.model.remote_backend.WithdrawRequest
import com.easy_pro_code.wallet.data.model.remote_backend.WithdrawResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CheckUserWebService {
    @Headers("authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    @POST("getuser")
    suspend fun checkUserByPhone(@Body checkUser:UserByPhoneRequest):UserByPhoneResponse
}