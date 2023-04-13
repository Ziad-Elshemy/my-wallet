//package com.easy_pro_code.wallet.HomeFlow.ViewModel
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
//import com.easy_pro_code.wallet.data.model.remote_backend.TransactionResponse
//import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
//import kotlinx.coroutines.launch
//
//class TransactionViewModel:ViewModel() {
//    val transactionLiveData:MutableLiveData<TransactionResponse> = MutableLiveData<TransactionResponse>()
//
//    private val transactionWebService = ApiManager.getTransactionHistory()
//
//    fun getTransaction(password:String){
//        viewModelScope.launch {
//            transactionLiveData.value = transactionWebService.getTransactionHistory(
//                TransactionRequest(userId = AuthUtils.manager.fetchData().id,password = password)
//            )
//        }
//    }
//
//}