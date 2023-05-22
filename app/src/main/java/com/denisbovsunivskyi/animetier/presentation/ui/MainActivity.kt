package com.denisbovsunivskyi.animetier.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.ui.setupWithNavController
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.activity.BaseNavigationActivity
import com.denisbovsunivskyi.animetier.databinding.ActivityMainBinding
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.goneView
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.showView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseNavigationActivity() {
    private val currentUser = Firebase.auth.currentUser

    private lateinit var binding: ActivityMainBinding
    private var saveInstance: Bundle? = null
    override fun setNavController(): Int {
        return R.id.nav_fragment_container
    }

    override fun init(savedInstanceState: Bundle?) {
        saveInstance = savedInstanceState
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onInitNavController() {
        currentUser?.reload()
        if (saveInstance == null) {
            if (currentUser == null) {
                mNavController.navigate(MainActivityDirections.actionGlobalAuth())
            } else {
                mNavController.navigate(MainActivityDirections.actionGlobalHomeList())
                Toast.makeText(this, "User sign ined", Toast.LENGTH_SHORT).show()
            }
        }

        binding.bottomNav.setupWithNavController(mNavController)

        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> isBottomNavVisible(false)
                R.id.registrationFragment -> isBottomNavVisible(false)
                R.id.registrationProfileInfoFragment -> isBottomNavVisible(false)
                else -> isBottomNavVisible(true)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun isBottomNavVisible(show: Boolean) {
        if (show) {
            binding.bottomNav.showView()
        } else {
            binding.bottomNav.goneView()
        }
    }
}