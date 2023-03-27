package com.denisbovsunivskyi.animetier.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.RegistrationViewModel
import com.example.animetier.R
import com.example.animetier.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<RegistrationViewModel>()
    private val authViewModel by viewModels<RegistrationViewModel>()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}