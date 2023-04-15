package com.easy_pro_code.wallet.HomeFlow.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.model.remote_backend.UserByPhoneRequest
import com.easy_pro_code.wallet.data.model.remote_backend.UserByPhoneResponse
import kotlinx.coroutines.launch

class CheckUserViewModel:ViewModel() {
    val checkUserLiveData=MutableLiveData<UserByPhoneResponse>()
    private val checkUserWebService=ApiManager.getCheckUserApi()
    fun checkUser(
        phone: String
    ){
        viewModelScope.launch {
            try {
                checkUserLiveData.value=checkUserWebService.checkUserByPhone(
                    UserByPhoneRequest(
                        phone = phone,
                    )
                )
            }catch (ex:Exception){

            }

        }
    }
}