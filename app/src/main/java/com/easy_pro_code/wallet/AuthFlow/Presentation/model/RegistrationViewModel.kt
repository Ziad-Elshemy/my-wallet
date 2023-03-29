package com.easy_pro_code.wallet.AuthFlow.Presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.api.web_services.AuthenticationWebService
import com.easy_pro_code.wallet.data.model.remote_backend.LoginResponse
import com.easy_pro_code.wallet.data.model.remote_backend.SignUpRequest
import com.easy_pro_code.wallet.data.model.remote_backend.SignUpResponse
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import retrofit2.HttpException
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val _userLiveData = MutableLiveData<SignUpResponse>()
    val userLiveData: LiveData<SignUpResponse>
        get() = _userLiveData

    private val authenticationWebService: AuthenticationWebService = ApiManager.getAuthenticationApi()

    fun signUp(userName:String,firstName:String,lastName:String,phoneNumber:String,password: String){

        viewModelScope.launch {
            try {
                val userRequest= SignUpRequest(
                    phone = phoneNumber,
                    userName = userName,
                    password = password
                )
                _userLiveData.value=authenticationWebService.signUp(userRequest)
            }catch (t:Throwable){
                when(t){
                    is HttpException ->{
                        t?.response()?.errorBody()
                        val response= SignUpResponse(message = "Connection failed,please try again")
                        _userLiveData.value=response
                    }
                }

            }catch (ex:Exception){

            }

        }
    }

    fun onSucessfulsignIn(user: LoginResponse, phoneNumber: String)
    {
        AuthUtils.manager.saveAuthToken(user,phoneNumber)
    }
}