package com.easy_pro_code.wallet.HomeFlow.Presentation.TellerDeposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.easy_pro_code.wallet.HomeFlow.view_model.TellerDepositViewModel
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.databinding.FragmentTellerDepositBinding
import java.util.regex.Pattern


class TellerDepositFragment : Fragment() {

    lateinit var viewBinding:FragmentTellerDepositBinding
    lateinit var tellerDepositViewmodel:TellerDepositViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tellerDepositViewmodel=ViewModelProvider(this).get(TellerDepositViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_teller_deposit, container, false)
        subscribeToLiveData()
        viewBinding.btnLogin.setOnClickListener {
            verifyUserData()
        }
        // Inflate the layout for this fragment
        return viewBinding.root
    }

    private fun verifyUserData() {
        if (!Pattern.matches(
                "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
                viewBinding.mobileNumberEt.text.toString()
            )
        ){
          viewBinding.mobileNumberEt.error="enter valid phone number"
        }else if (viewBinding.amountEt.text.toString().toInt()<5){
            viewBinding.amountEt.error="minimum deposit is 5"
        }else if (viewBinding.passwordEt.text.length<6){
            viewBinding.passwordEt.error="invalid password"
        }else{
//            tellerDepositViewmodel.deposit()
        }

    }

    private fun subscribeToLiveData() {

    }


}