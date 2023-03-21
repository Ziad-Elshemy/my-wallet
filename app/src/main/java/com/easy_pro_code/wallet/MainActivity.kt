package com.easy_pro_code.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.easy_pro_code.wallet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}