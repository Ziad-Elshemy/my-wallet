package com.easy_pro_code.wallet.data.api.web_services

import com.easy_pro_code.wallet.data.model.remote_backend.NationalInfoRequest
import com.easy_pro_code.wallet.data.model.remote_backend.NationalInfoResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NationalInfoWebService {
    @Headers("authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    @POST("user/NationalInfo")
    suspend fun createNationalInfo(@Body nationalInfoRequest: NationalInfoRequest):NationalInfoResponse
}