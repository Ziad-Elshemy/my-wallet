package com.easy_pro_code.wallet.HomeFlow.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.model.remote_backend.DepositRequest
import com.easy_pro_code.wallet.data.model.remote_backend.DepositResponse
import kotlinx.coroutines.launch

class TellerDepositViewModel:ViewModel() {
    val depositLiveData=MutableLiveData<DepositResponse>()
    val depositWebService=ApiManager.getDepositApi()
    fun deposit(
        userId:String,
        password:String,
        money:Int
    ){
        viewModelScope.launch {
            depositLiveData.value=depositWebService.deposit(DepositRequest(
                money=money,
                password=password,
                userId = userId
            ))
        }
    }
}