package com.easy_pro_code.wallet.AuthFlow.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy_pro_code.wallet.data.api.api_manager.ApiManager
import com.easy_pro_code.wallet.data.model.remote_backend.NationalInfoRequest
import com.easy_pro_code.wallet.data.model.remote_backend.NationalInfoResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class IdViewModel:ViewModel() {
    val nationalInfoLiveData=MutableLiveData<NationalInfoResponse>()
    private val nationalInfoWebService=ApiManager.getNationalIdApi()

    fun createNationalInfo(image:String,nationalId:String,profileImage:String,userId:String){
        viewModelScope.launch {
            try {

                nationalInfoLiveData.value=nationalInfoWebService.createNationalInfo(
                    NationalInfoRequest(
                        nationalIdImg = image,
                        nationalId=nationalId,
                        profileImg = profileImage,
                        userId=userId
                    )
                )
            }
            catch (E:HttpException){
                Log.i("413333333333" , E.response()?.errorBody()!!.string())
            }



        }
    }
}