package com.easy_pro_code.wallet.HomeFlow.ViewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.api.web_services.AuthenticationWebService
import com.easy_pro_code.wallet.data.api.web_services.ShowBalanceWebService
import com.easy_pro_code.wallet.data.model.remote_backend.*
import kotlinx.coroutines.launch
import retrofit2.HttpException

class GetBalanceViewModel:ViewModel() {

    private val _userLiveData = MutableLiveData<ShowBalanceResponse?>()

    val userLiveData: LiveData<ShowBalanceResponse?> get() = _userLiveData
    private val showBalancewebService: ShowBalanceWebService = ApiManager.getBalanceData()

    fun getBalance(Phone:String , Password:String){

        viewModelScope.launch {
            try {
                val userRequest= ShowBalanceRequest(
                    phone = Phone ,
                    password = Password
                )
                _userLiveData.value=showBalancewebService.showBalance(userRequest)
            }catch (t:Throwable){
                when(t){
                    is HttpException ->{
                        t.response()?.errorBody()
                        Log.i("Abanoub" , "Internet Error")
                    }
                }

            }catch (ex:Exception){

            }

        }
    }

}