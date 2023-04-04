package com.easy_pro_code.wallet.HomeFlow.Presentation.PaymentServices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.databinding.FragmentPaymentServicesBinding


class PaymentServicesFragment : Fragment() {

    lateinit var binding:FragmentPaymentServicesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_payment_services, container, false)

        binding.mobilrInternetCardView.setOnClickListener {
            findNavController().navigate(R.id.action_paymentServicesFragment_to_paymentServices2Fragment)
        }
        binding.electricityBillsCardView.setOnClickListener {
            findNavController().navigate(R.id.action_paymentServicesFragment_to_paymentServices2Fragment)
        }
        binding.waterCardView.setOnClickListener {
            findNavController().navigate(R.id.action_paymentServicesFragment_to_paymentServices2Fragment)
        }
        binding.gasCardView.setOnClickListener {
            findNavController().navigate(R.id.action_paymentServicesFragment_to_paymentServices2Fragment)
        }
        binding.travelCardView.setOnClickListener {
            findNavController().navigate(R.id.action_paymentServicesFragment_to_paymentServices2Fragment)
        }
        binding.educationCardView.setOnClickListener {
            findNavController().navigate(R.id.action_paymentServicesFragment_to_paymentServices2Fragment)
        }
        binding.purchasesCardView.setOnClickListener {
            findNavController().navigate(R.id.action_paymentServicesFragment_to_paymentServices2Fragment)
        }

        return binding.root


    }



}