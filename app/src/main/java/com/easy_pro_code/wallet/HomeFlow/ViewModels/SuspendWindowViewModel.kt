package com.easy_pro_code.wallet.HomeFlow.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SuspendWindowViewModel:ViewModel() {
    val suspendWindowLiveData: MutableLiveData<Boolean?> = MutableLiveData()

    fun progressBar(isVisible:Boolean){
        viewModelScope.launch {
            suspendWindowLiveData.value=isVisible
        }
    }
}