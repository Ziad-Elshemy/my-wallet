package com.easy_pro_code.wallet.AuthFlow.Presentation.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.api.web_services.AuthenticationWebService
import com.easy_pro_code.wallet.data.model.remote_backend.LoginRequest
import com.easy_pro_code.wallet.data.model.remote_backend.LoginResponse
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import kotlinx.coroutines.launch
import retrofit2.HttpException



class LoginViewModel : ViewModel(){


    private val _userLiveData = MutableLiveData<LoginResponse?>()
    val userLiveData: LiveData<LoginResponse?>
        get() = _userLiveData
    private val authenticationWebService: AuthenticationWebService = ApiManager.getAuthenticationApi()

    //sessionManager
    val sessionManager = AuthUtils.manager

    fun autoSignIn(): LoginResponse {
        return  sessionManager.fetchData()
    }
    fun onSucessfulsignIn(user: LoginResponse,phoneNumber: String)
    {
        sessionManager.saveAuthToken(user,phoneNumber)
    }


    fun logIn(phoneNumber:String , password:String){
        viewModelScope.launch {
            try {
                val userRequest= LoginRequest(phoneNumber,password)
//                Log.i("Ziad: error" , "try loginViewModel ${userRequest.toString()}")
                _userLiveData.value=authenticationWebService.loginIn(userRequest)
//                Log.i("Ziad: error" , _userLiveData.value.toString())
            }catch (t:Throwable){
                when(t){
                    is HttpException ->{
                        when (t.code())
                        {
                            400 -> {
                                val response=LoginResponse(message = "User Not found.")
                                _userLiveData.value=response
                                Log.i("Ziad: error" , "400")
                            }
                            else -> {
                                val response = LoginResponse(message = "something went wrong")
                                _userLiveData.value = response
                                Log.i("Ziad: error" , "Something went Wrong")
                            }
                        }

                    }
                }

            } catch (_: Exception) {

            }

        }
    }

    fun clearLiveData(){
        _userLiveData.value= null
    }



}

