package com.easy_pro_code.wallet.AuthFlow.Presentation.SignUp

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.uber_driver.AuthFlow.ViewModel.SignUpViewModel
import com.easy_pro_code.wallet.AuthFlow.Presentation.model.RegistrationViewModel
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.FirebaseUtils
import com.easy_pro_code.wallet.databinding.FragmentSignUpBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import okhttp3.internal.tls.OkHostnameVerifier.verify
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding

    lateinit var userName: EditText
    lateinit var phoneNumber: EditText
    lateinit var password: EditText
    lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpViewModel =ViewModelProvider(this).get(SignUpViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)

        initViews()

        subscribeLiveData()

        binding.btnSignup.setOnClickListener {
            onRegisterPressed()

        }

        return binding.root
    }

    private fun onRegisterPressed() {
        val phoneNumber = binding.etPhoneNumber
        val userName = binding.etUserName
        val password = binding.etPasswordInput
        if(userName.text.isNotEmpty() && phoneNumber.text.isNotEmpty() && password.text!!.isNotEmpty()){
            loadingState()
            signUpViewModel.signUp(

                phoneNumber.text.toString(),

                userName.text.toString(),

                password.text.toString()
            )
        }else if(userName.text.isEmpty() || userName.text.isBlank() || userName.text.length <= 4 || userName.text.length >= 15){
                userName.error = "user name can't be empty or less than 4 character"
            }
              else if(phoneNumber.text.isEmpty() || phoneNumber.text.isBlank() || phoneNumber.text.length != 11){
                phoneNumber.error = "phone number not correct"
            }
              else if(password.text!!.isEmpty() || password.text!!.isBlank() || password.text!!.length <= 8){
                password.error = "password can't be empty or more than 8 character"
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun sendPhoneNumber(callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
        val options = PhoneAuthOptions.newBuilder(FirebaseUtils.auth)
            .setPhoneNumber("+2" + binding.etPhoneNumber.text.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private fun subscribeLiveData() {
        signUpViewModel.userLiveData.observe(viewLifecycleOwner) {
            it?.let { signUpResponse ->
                Log.e("Message: ",signUpResponse.message.toString())
                if (signUpResponse.message.equals("Failed! User is already in use!")) {
                    Toast.makeText(
                        requireContext(),
                        "Failed! User is already in use!",
                        Toast.LENGTH_SHORT
                    ).show()
                    stopLoadingState()
                } else if (signUpResponse.message.equals("something went wrong")) {
                    Toast.makeText(requireContext(), "something went wrong", Toast.LENGTH_SHORT)
                        .show()
                    stopLoadingState()
                }
                else if (signUpResponse.message.equals("Connection failed,please try again")) {
                    Toast.makeText(requireContext(), "Connection failed,please try again", Toast.LENGTH_SHORT).show()
                    stopLoadingState()
                }
                else {
                    Toast.makeText(requireContext(),"successfully",Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun initViews() {
        var firstName: String = ""
        var lastName: String = ""
        userName = binding.etUserName
        phoneNumber = binding.etPhoneNumber
        password = binding.etPasswordInput
        binding.btnLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun loadingState() {
        binding.btnSignup.visibility = View.GONE
        binding.progressIndicator.visibility = View.VISIBLE

    }

    private fun stopLoadingState() {
        binding.btnSignup.visibility = View.VISIBLE
        binding.progressIndicator.visibility = View.GONE

    }




    }

