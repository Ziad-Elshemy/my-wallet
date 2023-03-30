package com.easy_pro_code.wallet.HomeFlow.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.easy_pro_code.wallet.R
import com.easy_pro_code.wallet.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
        val navController = Navigation.findNavController(this,R.id.fragmentContainerView)
        dataBinding.btnHomePage.setOnClickListener {
            navController.navigate(R.id.homeFragment,null,NavOptions.Builder().setPopUpTo(navController.graph.startDestinationId, true).build())
        }
        dataBinding.btnMenu.setOnClickListener {
            navController.navigate(R.id.menuFragment,null,NavOptions.Builder().setPopUpTo(R.id.menuFragment, true).build())
        }
    }
}