package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.databinding.FragmentHomeListBinding
import com.denisbovsunivskyi.animetier.presentation.ui.MainActivityDirections
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeList : Fragment() {
    private lateinit var binding: FragmentHomeListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeListBinding.bind(view)
        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(HomeListDirections.actionGlobalAuth())
        }

    }
}