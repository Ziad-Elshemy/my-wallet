package com.easy_pro_code.wallet.payment.payment_otp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.easy_pro_code.wallet.AuthFlow.AuthFragment.AuthenticationFragment
import com.easy_pro_code.wallet.AuthFlow.Presentation.otp.OtpFragmentArgs
import com.easy_pro_code.wallet.AuthFlow.Presentation.otp.OtpFragmentDirections
import com.easy_pro_code.wallet.HomeFlow.Presentation.HomeActivity
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_backend.LoginResponse
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.data.model.remote_firebase.FirebaseUtils
import com.easy_pro_code.wallet.data.model.remote_firebase.PhoneVerification
import com.easy_pro_code.wallet.databinding.FragmentOtpBinding
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class PaymentOtpFragment: AuthenticationFragment() {

    lateinit var binding: FragmentOtpBinding
    private lateinit var userData: LoginResponse
    private lateinit var phoneData: PhoneVerification
    lateinit var mVerificationId: String
    val otpFragmentArgs: PaymentOtpFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp, container, false)

        userData=otpFragmentArgs.userData
        phoneData=otpFragmentArgs.phoneData
        mVerificationId=phoneData.verificationId


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                AuthUtils.manager.deleteData()
                findNavController().popBackStack()
            }
        })

        val args: OtpFragmentArgs by navArgs()
        val verficationId: String = args.phoneData.verificationId

        binding.pinViewOtpCode.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length == 6) {
                    verifyPhoneNumber(verficationId, binding.pinViewOtpCode.text.toString())

                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


        return binding.root
    }

    private fun verifyPhoneNumber(verificationId: String, code: String) {
        if (code.length == 6 && TextUtils.isDigitsOnly(code)) {
            val credential = PhoneAuthProvider.getCredential(verificationId, code)
            Firebase.auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    AuthUtils.manager.saveAuthToken(userData,phoneData.phoneNumber)
                    if(userData.status == 0)
                    {
                        Toast.makeText(requireContext(), "you are blocked call service for more information", Toast.LENGTH_SHORT).show()
                    }
                    else if (userData.status == 2)
                    {
                        findNavController().navigate(PaymentOtpFragmentDirections.actionPaymentOtpFragmentToPaymentMoneyPayNow())
                    }
                    else if (userData.status == -1)
                    {
                        Toast.makeText(requireContext(), "Checking Your paper", Toast.LENGTH_SHORT).show()

                    }
                    else if(userData.status == 1)
                    {
                        Toast.makeText(requireContext(), "your data is under checking,try again after 24h", Toast.LENGTH_SHORT).show()
                    }


                } else
                {
                    Toast.makeText(requireContext(), "Enter Valid Code", Toast.LENGTH_LONG).show()
                }

            }
        } else {
            Log.i("Error", "Error code")
        }
    }

    override fun successState(verificationId: String, token: PhoneAuthProvider.ForceResendingToken)
    {
        Toast.makeText(requireContext(), "Welcome", Toast.LENGTH_LONG).show()
        /// by navArgs

//        AuthUtils.manager.saveAuthToken(user,phoneNumber)

    }

    override fun errorState() {
        binding.progressPar.visibility = View.GONE
        Toast.makeText(requireContext(), "Enter Valid Code", Toast.LENGTH_LONG).show()
    }

    override fun loadingState() {
        val keyboard =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(binding.root.windowToken, 0)
        binding.progressPar.visibility = View.VISIBLE
    }



}