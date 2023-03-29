package com.easy_pro_code.wallet.AuthFlow.Presentation.Registeration

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.AuthFlow.view_model.IdViewModel
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.databinding.FragmentRegisterationBinding
import java.io.ByteArrayOutputStream


class RegisterationFragment : Fragment() {
    lateinit var registerationViewModel:IdViewModel
    lateinit var viewBinding:FragmentRegisterationBinding

    private val registerForProfileResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK ){
            viewBinding.profilePhoto.setImageURI(result.data?.data)
        }
    }

    private val registerForIdentityResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK ){
            viewBinding.identityPhoto.setImageURI(result.data?.data)
        }
    }

    val IMAGE_REQUEST_CODE=1_000_001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerationViewModel=ViewModelProvider(this).get(IdViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_registeration,container,false)
        subscribeToLiveData()
        viewBinding.exploreOtpBtn.setOnClickListener {
            verify()
        }
        viewBinding.uploadProfilePhotoBtn.setOnClickListener {
            pickUpLicenceImage(registerForProfileResult)
        }
        viewBinding.uploadIdentityPhotoBtn.setOnClickListener {
            pickUpLicenceImage(registerForIdentityResult)
        }

        // Inflate the layout for this fragment
        return viewBinding.root
    }

    private fun pickUpLicenceImage(registerForActivityResult: ActivityResultLauncher<Intent>) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        registerForActivityResult.launch(intent)

    }

    private fun subscribeToLiveData() {
        registerationViewModel.nationalInfoLiveData.observe(viewLifecycleOwner){
            it?.date?.let {
                findNavController().navigate(RegisterationFragmentDirections.actionRegisterationFragmentToSuccessFullRegisterationFragment())
            }
        }
    }

    private fun verify() {

        val nationalId=viewBinding.identityEt.text.toString()
        val userId=AuthUtils.manager.fetchData().id
        userId?.let {
            val imageMap=(viewBinding.identityPhoto.drawable as BitmapDrawable).bitmap
            val bos1 = ByteArrayOutputStream()
            imageMap.compress(CompressFormat.JPEG, 100, bos1)
            val bb1: ByteArray = bos1.toByteArray()
            val profileImageMap = (viewBinding.profilePhoto.drawable as BitmapDrawable).bitmap
            val bos2 = ByteArrayOutputStream()
            profileImageMap.compress(CompressFormat.JPEG, 100, bos2)
            val bb2: ByteArray = bos2.toByteArray()
            try {
                val imageString=Base64.encodeToString(bb1,Base64.DEFAULT)
                val profileImageString=Base64.encodeToString(bb2,Base64.DEFAULT)
                if (nationalId.isEmpty()||nationalId.length<14){
                    viewBinding.identityEt.error="invalid id"
                }else{
                    registerationViewModel.createNationalInfo(
                        image =imageString,
                        nationalId=nationalId,
                        profileImage = profileImageString,
                        userId=userId
                    )
                }
            }catch (ex:java.lang.Exception){

            }
        }
    }

}