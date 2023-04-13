package com.easy_pro_code.wallet.HomeFlow.Presentation.TellerDeposit

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.HomeFlow.ViewModels.GetBalanceViewModel
import com.easy_pro_code.wallet.HomeFlow.ViewModels.SuspendWindowViewModel
import com.easy_pro_code.wallet.HomeFlow.view_model.TellerDepositViewModel
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.databinding.FragmentTellerDepositBinding
import java.io.ByteArrayOutputStream


class TellerDepositFragment : Fragment() {

    lateinit var viewBinding:FragmentTellerDepositBinding
    lateinit var tellerDepositViewmodel:TellerDepositViewModel
    val  balanceViewModel: GetBalanceViewModel by activityViewModels()
    private val suspendWindowViewModel: SuspendWindowViewModel by activityViewModels()
    var imageFlag=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tellerDepositViewmodel=ViewModelProvider(this).get(TellerDepositViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var userPhone= AuthUtils.manager.getPhone().toString()
        userPhone = userPhone.replace("+2" , "")
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_teller_deposit, container, false)
        subscribeToLiveData()
        viewBinding.btnLogin.setOnClickListener {
            verifyUserData()
        }
        viewBinding.showBalance.setOnClickListener {
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
                        viewBinding.BalanceTv.text = it.balance.toString()
                    }

                }
                dialog_balance.dismiss()
            }


        }
        viewBinding.uploadIdentityPhotoBtn.setOnClickListener {
            pickUpLicenceImage(registerForIdentityResult)
        }

        // Inflate the layout for this fragment
        return viewBinding.root
    }

    private fun verifyUserData() {
        if (viewBinding.amountEt.text.isEmpty()||viewBinding.amountEt.text.toString().toInt()<5){
            viewBinding.amountEt.error="minimum deposit is 5"
        }else if (viewBinding.passwordEtInput.text==null|| viewBinding.passwordEtInput.text?.isEmpty() == true ||viewBinding.passwordEtInput.text!!.length<3){
            viewBinding.passwordEt.error="invalid password"
        }else{
            if (imageFlag){
                suspendWindowViewModel.progressBar(true)
                val imageMap=(viewBinding.identityPhoto.drawable as BitmapDrawable).bitmap
                val bos1 = ByteArrayOutputStream()
                imageMap.compress(Bitmap.CompressFormat.JPEG, 100, bos1)
                val bb1: ByteArray = bos1.toByteArray()
                try {
                    val imageString= Base64.encodeToString(bb1, Base64.DEFAULT)
                    tellerDepositViewmodel.deposit(
                        AuthUtils.manager.fetchData().id!!,
                        viewBinding.passwordEtInput.text.toString(),
                        viewBinding.amountEt.text.toString().toInt(),
                        imageString
                    )
                }catch (ex:java.lang.Exception){

                }
            }else{
                Toast.makeText(requireContext(), "Please upload recite first", Toast.LENGTH_SHORT).show()
            }


        }

    }

    private fun subscribeToLiveData() {
        tellerDepositViewmodel.depositLiveData.observe(viewLifecycleOwner){
            it?.let {
                suspendWindowViewModel.progressBar(false)
                Toast.makeText(requireContext(), "We will review your transaction and reply soon", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }


    private fun pickUpLicenceImage(registerForActivityResult: ActivityResultLauncher<Intent>) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        registerForActivityResult.launch(intent)

    }

    private val registerForIdentityResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK ){
            viewBinding.identityPhoto.setImageURI(result.data?.data)
            imageFlag=true
        }
    }


}