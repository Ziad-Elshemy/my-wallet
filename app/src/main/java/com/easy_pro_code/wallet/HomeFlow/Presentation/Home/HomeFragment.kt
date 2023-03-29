package com.easy_pro_code.wallet.HomeFlow.Presentation.Home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.HomeFlow.ViewModels.GetBalanceViewModel
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val  balanceViewModel: GetBalanceViewModel by activityViewModels()

    var userPhone= AuthUtils.manager.getPhone().toString()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        userPhone = userPhone.replace("+2" , "")
        binding.moneyTransferIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_moneyTransferFragment)
        }

        binding.userName.setText("Hi, "+AuthUtils.manager.fetchData().userName)
        binding.userName.isEnabled = false

        binding.price.setOnClickListener {

            val view = layoutInflater.inflate(R.layout.dialog_balance ,null)

            val dialog_balance = AlertDialog.Builder(requireContext()).setView(view).create()

            dialog_balance.show()
            val cancelButton: Button = view.findViewById(R.id.cancel_btn)

            cancelButton.setOnClickListener {
                dialog_balance.dismiss()
            }
            val okButton: Button = view.findViewById(R.id.ShowBtn)
            val password: EditText = view.findViewById(R.id.passwordET)


            okButton.setOnClickListener {
                balanceViewModel.getBalance(userPhone,password.text.toString())

                balanceViewModel.userLiveData.observe(viewLifecycleOwner)
                {
                    if(it?.balance ==null)
                    {
                        Toast.makeText(context, "retry, Invalid Password", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        binding.price.setText(it.balance.toString())
                    }

                }


                dialog_balance.dismiss()
            }

        }
        return binding.root
    }

}