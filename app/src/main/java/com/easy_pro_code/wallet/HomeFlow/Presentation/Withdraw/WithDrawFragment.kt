package com.easy_pro_code.wallet.HomeFlow.Presentation.Withdraw

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.easy_pro_code.wallet.HomeFlow.ViewModels.GetBalanceViewModel
import com.easy_pro_code.wallet.HomeFlow.ViewModels.SuspendWindowViewModel
import com.easy_pro_code.wallet.HomeFlow.ViewModels.WithdrawViewModel
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.databinding.FragmentWithDrawBinding


class WithDrawFragment : Fragment() {

    private lateinit var binding:FragmentWithDrawBinding
    private val  balanceViewModel: GetBalanceViewModel by activityViewModels()

    private lateinit var withDrawViewModel:WithdrawViewModel

    private val suspendWindowViewModel: SuspendWindowViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        withDrawViewModel=ViewModelProvider(this).get(WithdrawViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var userPhone= AuthUtils.manager.getPhone().toString()
        userPhone = userPhone.replace("+2" , "")
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_with_draw, container, false)

        binding.showBalance.setOnClickListener {
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
                suspendWindowViewModel.progressBar(true)
                balanceViewModel.getBalance(userPhone,password.text.toString())

                balanceViewModel.userLiveData.observe(viewLifecycleOwner)
                {
                    suspendWindowViewModel.progressBar(false)

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

        withDrawViewModel.withdrawLiveData.observe(viewLifecycleOwner){
            it?.let {
                if(it.code()==200){
                    suspendWindowViewModel.progressBar(false)
                    Toast.makeText(requireContext(), "We will review your transaction and reply soon", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnConfirm.setOnClickListener {
            validateRequest(
                binding.passwordEtInput.text.toString(),
                binding.amountEt.text.toString(),
                binding.nameEtInput.text.toString(),
                binding.phoneEt.text.toString()
            )
        }
        return binding.root
    }

    private fun validateRequest(
        password: String,
        amount: String,
        name: String,
        phone: String
    ) {
        suspendWindowViewModel.progressBar(true)
        if (password.isEmpty()){
            binding.passwordEtInput.error=getString(R.string.enter_your_password)
        }else if (amount.isEmpty()){
            binding.amountEt.error=getString(R.string.enter_your_mail)
        } else if (name.isEmpty()){
            binding.nameEt.error=getString(R.string.enter_your_mail)
        }else if (phone.isEmpty()){
            binding.phoneEt.error=getString(R.string.enter_your_mail)
        }else{
            withDrawViewModel.withdraw(
                password =password ,
                money =amount.toInt(),
                name=name,
                phone=phone
            )
        }

    }

}