package com.easy_pro_code.wallet.HomeFlow.Presentation.Withdraw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.databinding.FragmentWithDrawBinding


class WithDrawFragment : Fragment() {

    lateinit var binding:FragmentWithDrawBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_with_draw, container, false)

        val amount = binding.amountEt
        val password = binding.passwordEtInput


        binding.btnConfirm.setOnClickListener {
            if(amount.text.isEmpty()){
                amount.error="Amount is required"
            }
            if ( password.text.isEmpty()){
                password.error="Password is required"
            }
        }



        return binding.root
    }

}