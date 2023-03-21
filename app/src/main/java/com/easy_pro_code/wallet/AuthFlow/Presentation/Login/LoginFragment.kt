package com.easy_pro_code.wallet.AuthFlow.Presentation.Login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.AuthFlow.Presentation.model.LoginViewModel
import com.easy_pro_code.wallet.AuthFragment.AuthenticationFragment
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.FirebaseUtils
import com.easy_pro_code.wallet.data.model.remote_firebase.PhoneVerification
import com.easy_pro_code.wallet.databinding.FragmentLoginBinding
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class LoginFragment : AuthenticationFragment() {

    lateinit var binding:FragmentLoginBinding

    private lateinit var loginViewModel:LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel =ViewModelProvider(this).get(LoginViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner=viewLifecycleOwner

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)

        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_otpFragment)
        }

        startState()
        initViews()




        return binding.root
    }


    private fun subscribeToLiveData() {
        loginViewModel.userLiveData.observe(viewLifecycleOwner){
            it?.let {
                    response->
                binding.progressBarLoadingPhoneAuth.visibility = View.GONE
                binding.btnLogin.visibility = View.GONE
//                Log.e("Ziad Response",binding.etPhoneNumber.text.toString())
                if (response.message.equals("user not found")){
                    Toast.makeText(requireContext(), "please sign up first", Toast.LENGTH_SHORT).show()
                    loginViewModel.clearLiveData()

                    findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
                }else if(response.message.equals("something went wrong")){
                    Toast.makeText(requireContext(), "something went wrong", Toast.LENGTH_SHORT).show()
                } else{
                    sendPhoneNumber(callbacks)
                    loginViewModel.onSucessfulsignIn(
                        response,
                        binding.etPhoneNumber.text.toString()
                    )
                }
            }

        }
    }

    private fun initViews(){
        binding.signUpBtn.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_signUpFragment) }

        binding.btnLogin.setOnClickListener {
            checkPhoneNumber()
        }
    }

    private fun checkPhoneNumber() {

        val phoneNumber = binding.etPhoneNumber.text
        if (phoneNumber.isBlank() || phoneNumber.isEmpty() || !TextUtils.isDigitsOnly(
                phoneNumber
            ) || phoneNumber.length != 11
        ) {

            Toast.makeText(requireContext(), "Enter Valid Number ${phoneNumber.length}", Toast.LENGTH_LONG).show()
        } else {
            //Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
            loadingState()
            Log.i("Ziad: error" , "loginFragment ${phoneNumber.toString()}")
            loginViewModel.logIn(phoneNumber.toString())
        }
    }

    private fun sendPhoneNumber(callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
        val options = PhoneAuthOptions.newBuilder(FirebaseUtils.auth)
            .setPhoneNumber("+2"+binding.etPhoneNumber.text.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }
    override fun loadingState() {
        binding.progressBarLoadingPhoneAuth.visibility = View.VISIBLE
        binding.btnLogin.visibility = View.GONE
        Log.i("Ziad: error" , "loadingState")
    }

    override fun successState(verificationId: String , token: PhoneAuthProvider.ForceResendingToken) {
        binding.progressBarLoadingPhoneAuth.visibility = View.GONE
        binding.btnLogin.visibility = View.GONE

        Log.i("Ziad: error" , "successState")

        //navigation
        val action = LoginFragmentDirections.actionLoginFragmentToOtpFragment(verificationId)
        //this must be passed on argument in nav_graph <<<<---------------------------------------
        FirebaseUtils.token=token
        action.arguments.putParcelable("verification",
            PhoneVerification(verificationId,token,"+2"+binding.btnLogin.text.toString())
        )

    }
    override fun errorState() {
        binding.progressBarLoadingPhoneAuth.visibility = View.GONE
        binding.btnLogin.visibility = View.VISIBLE
        Log.i("Ziad: error" , "errorState")
        Toast.makeText(requireContext(), "Please try again", Toast.LENGTH_LONG).show()
    }
    private fun startState() {
        binding.progressBarLoadingPhoneAuth.visibility = View.GONE
        Log.i("Ziad: error" , "startState")
        binding.btnLogin.visibility = View.VISIBLE
    }
    companion object{
        val TAG="phoneAuthFragment"
    }









}