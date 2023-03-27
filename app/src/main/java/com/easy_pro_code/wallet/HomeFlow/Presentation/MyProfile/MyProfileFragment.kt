package com.easy_pro_code.wallet.HomeFlow.Presentation.MyProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.databinding.FragmentMyProfileBinding


class MyProfileFragment : Fragment() {

    lateinit var binding : FragmentMyProfileBinding
    val sessionManager = AuthUtils.manager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_profile, container, false)

        binding.myAccountName.setText(AuthUtils.manager.fetchData().userName)

        binding.name.setText(AuthUtils.manager.fetchData().userName)
        binding.name.isEnabled = false

        binding.phone.setText(AuthUtils.manager.getPhone())
        binding.phone.isEnabled = false

        return binding.root
    }

}