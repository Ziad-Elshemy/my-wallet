package com.easy_pro_code.uber_driver.AuthFlow.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.api.web_services.AuthenticationWebService
import com.easy_pro_code.wallet.data.model.remote_backend.LoginResponse
import com.easy_pro_code.wallet.data.model.remote_backend.SignUpRequest
import com.easy_pro_code.wallet.data.model.remote_backend.SignUpResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException


class SignUpViewModel : ViewModel(){
    private val _userLiveData = MutableLiveData<SignUpResponse>()
    val userLiveData: LiveData<SignUpResponse>
        get() = _userLiveData



    private val authenticationWebService: AuthenticationWebService = ApiManager.getAuthenticationApi()


    fun signUp(phoneNumber:String , userName:String , passowrd:String){

        viewModelScope.launch {
            try {
                val userSignUpRequest= SignUpRequest(phone = phoneNumber , userName = userName , password = passowrd )

                _userLiveData.value=authenticationWebService.signUp(userSignUpRequest)


            }catch (t:Throwable){
                when(t){
                    is HttpException ->{
                        when(t.code()){
                            400->{
                                t.response()?.errorBody()
                                val response= SignUpResponse(message = "Failed! User is already in use!")
                                _userLiveData.value=response
                            }else->{
                                val response = SignUpResponse(message = "something went wrong")
                                _userLiveData.value = response
                                Log.i("Ziad: error" , "Something went Wrong")
                            }

                        }
                    }
                }

            }catch (ex:Exception){

            }

        }
    }
}