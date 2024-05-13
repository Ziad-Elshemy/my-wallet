package com.easy_pro_code.wallet.AuthFlow.Presentation.Login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.AuthFlow.Presentation.model.LoginViewModel
import com.easy_pro_code.wallet.AuthFlow.AuthFragment.AuthenticationFragment
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_backend.LoginResponse
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.data.model.remote_firebase.FirebaseUtils
import com.easy_pro_code.wallet.data.model.remote_firebase.PhoneVerification
import com.easy_pro_code.wallet.databinding.FragmentLoginBinding
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class LoginFragment : AuthenticationFragment() {

    lateinit var phoneEditText: EditText
    lateinit var passEditText: EditText
    lateinit var binding:FragmentLoginBinding
    private lateinit var loginViewModel:LoginViewModel
    private  var verificationId:String=""
    private lateinit var userData: LoginResponse


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
        startState()
        initViews()
        subscribeToLiveData()

        if (AuthUtils.manager.getToken() !=null){
            binding.etPhoneNumber.setText(AuthUtils.manager.fetchData().phone.toString())
        }


        return binding.root
    }


    private fun subscribeToLiveData() {
        loginViewModel.userLiveData.observe(viewLifecycleOwner){
            it?.let {
                    response->
                binding.progressBarLoadingPhoneAuth.visibility = View.GONE
                binding.btnLogin.visibility = View.GONE

                Log.e("Ziad Response",binding.etPhoneNumber.text.toString())

                if (response.message.equals("you are not a user")){
                    Toast.makeText(requireContext(), "you are not a user", Toast.LENGTH_SHORT).show()
                    loginViewModel.clearLiveData()
                    binding.btnLogin.visibility=View.VISIBLE
                    //findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
                }
                else if(response.error.equals("Invalid user")){
                    Toast.makeText(requireContext(), "Invalid user ,please sign up first", Toast.LENGTH_SHORT).show()
                    loginViewModel.clearLiveData()
                    findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
                }
                else{
                    /////////user found ---> Go to Otp Page
                    sendPhoneNumber(callbacks)
                    loginViewModel.onSucessfulsignIn(response, binding.etPhoneNumber.text.toString())
                    userData = response
                    Log.i("token" , AuthUtils.manager.getToken().toString())
                    Log.i("phone" , AuthUtils.manager.fetchData().phone.toString())
                    Log.i("username" , AuthUtils.manager.fetchData().userName.toString())
                }
            }

        }
    }

    private fun initViews(){
        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.btnLogin.setOnClickListener {
            checkPhoneNumber()
        }
    }

    private fun checkPhoneNumber() {
        phoneEditText = binding.etPhoneNumber
        passEditText = binding.etPasswordInput
        val phoneNumber = phoneEditText.text.toString().trim()
        val password = passEditText.text.toString().trim()
        if (phoneNumber.isBlank() || phoneNumber.isEmpty() || !TextUtils.isDigitsOnly(phoneNumber)
            || phoneNumber.length != 11) {
            phoneEditText.error = "Enter Valid Number"
            Toast.makeText(requireContext(), "Enter Valid Number", Toast.LENGTH_LONG).show()
        }
        else if (password.isBlank() || password.isEmpty()){
            passEditText.error = "Enter Password"
            Toast.makeText(requireContext(), "Enter Password", Toast.LENGTH_LONG).show()
        }

        else {
            //Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
            loadingState()
            Log.i("Ziad: error" , "loginFragment ${phoneNumber.toString()}")
            loginViewModel.logIn(phoneNumber.toString(),password.toString())
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
        val phoneData=PhoneVerification(verificationId,token,"+2"+binding.etPhoneNumber.text.toString())

        val action = LoginFragmentDirections.actionLoginFragmentToOtpFragment(phoneData,userData)
        //this must be passed on argument in nav_graph <<<<---------------------------------------
        FirebaseUtils.token=token
//        action.arguments.putParcelable("verification",
//            PhoneVerification(verificationId,token,"+2"+binding.etPhoneNumber.text.toString()))
        findNavController().navigate(action)

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