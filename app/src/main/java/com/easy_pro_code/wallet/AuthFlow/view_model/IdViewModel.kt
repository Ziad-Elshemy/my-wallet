package com.easy_pro_code.wallet.AuthFlow.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.model.remote_backend.NationalInfoRequest
import com.easy_pro_code.wallet.data.model.remote_backend.NationalInfoResponse
import kotlinx.coroutines.launch

class IdViewModel:ViewModel() {
    val nationalInfoLiveData=MutableLiveData<NationalInfoResponse>()
    private val nationalInfoWebService=ApiManager.getNationalIdApi()

    fun createNationalInfo(image:String,nationalId:String,profileImage:String,userId:String){
        viewModelScope.launch {

            nationalInfoLiveData.value=nationalInfoWebService.createNationalInfo(
                NationalInfoRequest(
                nationalIdImg = image,
                nationalId=nationalId,
                profileImg = profileImage,
                userId=userId
                )
            )
        }
    }
}