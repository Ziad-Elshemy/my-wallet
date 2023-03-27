package com.easy_pro_code.wallet.HomeFlow.Presentation.MoneyTransfer

import android.app.AlertDialog
import android.content.Intent
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
import com.easy_pro_code.wallet.HomeFlow.ViewModels.GetBalanceViewModel
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.databinding.FragmentMoneyTransferBinding


class MoneyTransferFragment : Fragment() {
    lateinit var  binding: FragmentMoneyTransferBinding
    val  balanceViewModel: GetBalanceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userPhone=AuthUtils.manager.getPhone()
        binding = FragmentMoneyTransferBinding.inflate(layoutInflater)

        binding.showBalance.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.dialog_balance ,null)

            val dialog_balance = AlertDialog.Builder(requireContext()).setView(view).create()

            dialog_balance.show()
            val cancelButton: Button = view.findViewById(R.id.cancel_btn)

            cancelButton.setOnClickListener {
                dialog_balance.dismiss()
            }
            val okButton: Button = view.findViewById(R.id.ShowBtn)
            val password:EditText = view.findViewById(R.id.passwordET)

            okButton.setOnClickListener {
                balanceViewModel.getBalance(userPhone!!,password.text.toString())

                balanceViewModel.userLiveData.observe(viewLifecycleOwner)
                {
                    if(it?.balance ==null)
                    {
                        Toast.makeText(context, "retry, Invalid Password", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        binding.BalanceTv.setText(it.balance.toString())
                    }

                }

                dialog_balance.dismiss()
            }


        }

        // Inflate the layout for this fragment
        return binding.root
    }

}