package com.easy_pro_code.wallet.data.api.web_services

import com.easy_pro_code.wallet.data.model.remote_backend.SignInRequest
import com.easy_pro_code.wallet.data.model.remote_backend.SignUp
import com.easy_pro_code.wallet.data.model.remote_backend.SignUpRequest
import com.easy_pro_code.wallet.data.model.remote_backend.UserData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationWebService  {

    @Headers("Authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    @POST("signup")
    suspend fun signUp(@Body User: SignUpRequest): SignUp

    @Headers("Authorization:Basic UIOIKMJOYWRtaW46cGFzc3dvcmQ=")
    @POST("login")
    suspend fun signIn(@Body user: SignInRequest): UserData
}