package com.easy_pro_code.wallet.HomeFlow.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.model.remote_backend.TransferRequest
import com.easy_pro_code.wallet.data.model.remote_backend.TransferResponse
import kotlinx.coroutines.launch

class TransferViewModel: ViewModel() {
    val LiveData= MutableLiveData<TransferResponse?>()
    val transferWebServices= ApiManager.getTransferApi()

    fun transferBalance(
        receiver:String,
        cashTransfer:Int,
        userId: String,
        password:String
    ) {
        viewModelScope.launch {
            LiveData.value = transferWebServices.transferBalance(TransferRequest(
                receiver = receiver ,
                cashTransfer = cashTransfer ,
                userId = userId,
                password = password
            ))

        }

    }
}