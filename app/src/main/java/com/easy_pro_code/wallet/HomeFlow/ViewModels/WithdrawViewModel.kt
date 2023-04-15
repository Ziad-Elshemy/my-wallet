package com.easy_pro_code.wallet.HomeFlow.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.model.remote_backend.WithdrawRequest
import com.easy_pro_code.wallet.data.model.remote_backend.WithdrawResponse
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import kotlinx.coroutines.launch
import retrofit2.Response

class WithdrawViewModel:ViewModel() {
    val withdrawLiveData=MutableLiveData<WithdrawResponse>()
    private val withdrawWebService=ApiManager.getWithdrawApi()
    fun withdraw(
        password: String,
        money: Int,
        name: String,
        phone: String
    ){
        viewModelScope.launch {
            try {
                val user=AuthUtils.manager.fetchData()
                withdrawLiveData.value=withdrawWebService.withdraw(
                    WithdrawRequest(
                        money=money,
                        phone = phone,
                        userId = user.id,
                        password=password,
                        name = name
                    )
                )
            }catch (ex:Exception){

            }

        }
    }
}