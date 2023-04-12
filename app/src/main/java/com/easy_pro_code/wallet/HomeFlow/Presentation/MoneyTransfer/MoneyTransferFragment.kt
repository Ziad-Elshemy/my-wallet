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
import com.easy_pro_code.wallet.HomeFlow.ViewModels.TransferViewModel
import com.easy_pro_code.wallet.HomeFlow.model.ErrorHandelation
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.databinding.FragmentMoneyTransferBinding


class MoneyTransferFragment : Fragment() {
    lateinit var  binding: FragmentMoneyTransferBinding
    val  balanceViewModel: GetBalanceViewModel by activityViewModels()
    val  transferViewModel:TransferViewModel by activityViewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var userPhone=AuthUtils.manager.getPhone().toString()
        userPhone = userPhone.replace("+2" , "")


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

                balanceViewModel.getBalance(userPhone,password.text.toString())

                balanceViewModel.userLiveData.observe(viewLifecycleOwner)
                {
                    if(it?.balance ==null)
                    {
                        Toast.makeText(context, "retry, Invalid Password", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        binding.BalanceTv.text = it.balance.toString()
                    }

                }


                dialog_balance.dismiss()
            }


        }

        val phone = binding.mobileNumberEt
        val amount = binding.amountEt
        val password = binding.passwordEtInput


        binding.TransferBtn.setOnClickListener {

            if (phone.text.isEmpty()){
                phone.error="Phone is required"
            }
            if(amount.text.isEmpty()){
                amount.error="Amount is required"
            }
            if ( password.text.isEmpty()){
                password.error="Password is required"
            }
            else{
                transferViewModel.transferBalance(
                    receiver =  binding.mobileNumberEt.text.toString(),
                    cashTransfer = binding.amountEt.text.toString().toInt(),
                    userId = AuthUtils.manager.fetchData().id.toString(),
                    password = binding.passwordEtInput.text.toString()
                )
                transferViewModel.LiveData.observe(viewLifecycleOwner)
                {
//                if(it?.toString().equals("sorry,you don't have enough")){
//                    Toast.makeText(requireContext(), "sorry,you don't have enough", Toast.LENGTH_SHORT).show()
//                }else{

                    //// Error Handlation
                    it?.transfer?.id?.let{
                        Toast.makeText(requireContext(), "Transaction Done", Toast.LENGTH_SHORT).show()
                        binding.BalanceTv.text = "*************"
                        binding.mobileNumberEt.text.clear()
                        binding.passwordEtInput.text.clear()
                        binding.amountEt.text.clear()
                    }

                    if( it?.transfer?.id == null && it?.message ==null)
                    {
                        Toast.makeText(requireContext(), "Try Again", Toast.LENGTH_SHORT).show()
                    }
                    else if (it.message != null){
                        Toast.makeText(requireContext(), it.message.toString() , Toast.LENGTH_SHORT).show()
                        Log.e("Ziad Transfer message",it.message.toString())
                    }

                }
            }
            transferViewModel.LiveData.value == null




//            }


        }

//        subscribeToLiveData()


        // Inflate the layout for this fragment
        return binding.root
    }


    fun subscribeToLiveData(){
        ErrorHandelation.errorLiveData.observe(viewLifecycleOwner)
        {
            Toast.makeText(requireContext(), it.toString() , Toast.LENGTH_SHORT).show()
            Log.e("Ziad Transfer",it.toString())
        }
    }

}