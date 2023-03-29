package com.easy_pro_code.wallet.HomeFlow.Presentation.Menu

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.easy_pro_code.wallet.MainActivity
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.data.model.remote_firebase.AuthUtils
import com.easy_pro_code.wallet.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    lateinit var binding : FragmentMenuBinding
    val sessionManager = AuthUtils.manager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate( inflater , R.layout.fragment_menu, container, false)

        binding.myTransactionBtn.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToMyTransactionFragment())
        }
        binding.privacySecurityBtn.setOnClickListener {
            Toast.makeText(requireContext(), "coming soon", Toast.LENGTH_SHORT).show()
        }
        binding.supportBtn.setOnClickListener {
            Toast.makeText(requireContext(), "coming soon", Toast.LENGTH_SHORT).show()
        }
        binding.aboutBtn.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToAboutFragment2())
        }
        binding.logOutBtn.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.logout_dialog,null)
            val logoutBoxBuilder = AlertDialog.Builder(requireContext()).setView(view).create()
            logoutBoxBuilder.show()
            val cancelButton: Button = view.findViewById(R.id.btn_cancel)
            cancelButton.setOnClickListener {
                logoutBoxBuilder.dismiss()
            }
            val okButton: Button = view.findViewById(R.id.btn_ok)
            okButton.setOnClickListener {

                sessionManager.deleteData()

                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }

        return binding.root
    }

}