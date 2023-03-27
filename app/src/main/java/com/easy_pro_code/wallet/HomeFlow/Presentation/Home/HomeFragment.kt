package com.easy_pro_code.wallet.HomeFlow.Presentation.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.moneyTransferIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_moneyTransferFragment)
        }
        return binding.root
    }

}