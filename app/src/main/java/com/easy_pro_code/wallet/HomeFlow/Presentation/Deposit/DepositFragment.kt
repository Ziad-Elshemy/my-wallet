package com.easy_pro_code.wallet.HomeFlow.Presentation.Deposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.databinding.FragmentDepositBinding


class DepositFragment : Fragment() {

    private lateinit var viewBinding:FragmentDepositBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_deposit,container,false)
        viewBinding.fromTeller.setOnClickListener {
            findNavController().navigate(DepositFragmentDirections.actionDepositFragmentToTellerDepositFragment())
        }
        viewBinding.bankCardView.setOnClickListener {
            Toast.makeText(requireContext(), "Coming Soon", Toast.LENGTH_SHORT).show()
        }
        return viewBinding.root
    }
}