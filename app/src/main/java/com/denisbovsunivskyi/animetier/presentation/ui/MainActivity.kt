package com.denisbovsunivskyi.animetier.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.activity.BaseNavigationActivity
import com.denisbovsunivskyi.animetier.databinding.ActivityMainBinding
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.RegistrationViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : BaseNavigationActivity() {
    private val viewModel by viewModels<RegistrationViewModel>()
    private val authViewModel by viewModels<RegistrationViewModel>()
    private val currentUser = Firebase.auth.currentUser

    private lateinit var binding : ActivityMainBinding

    override fun setNavController(): Int {
        return R.id.nav_fragment_container
    }

    override fun init(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onInitNavController() {
        currentUser?.reload()
        if (currentUser == null) {
            mNavController.navigate(MainActivityDirections.actionGlobalAuth())
        }else{
            mNavController.navigate(MainActivityDirections.actionGlobalAuth())

            Toast.makeText(this, "User sign ined", Toast.LENGTH_SHORT).show()
        }
    }
}