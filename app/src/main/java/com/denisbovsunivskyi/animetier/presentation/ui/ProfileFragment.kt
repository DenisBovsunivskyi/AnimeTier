package com.denisbovsunivskyi.animetier.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.databinding.FragmentProfileBinding
import com.denisbovsunivskyi.animetier.presentation.ui.fragments.HomeListDirections
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : BaseBindingFragment<FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override fun init() {
        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(HomeListDirections.actionGlobalAuth())
        }
    }


}