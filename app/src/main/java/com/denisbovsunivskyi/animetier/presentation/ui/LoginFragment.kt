package com.denisbovsunivskyi.animetier.presentation.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.AuthViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.colorizeEnd
import com.example.animetier.R
import com.example.animetier.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        initViews()
        initListeners()

    }

    private fun initListeners() {
        with(binding) {
            haventAccountBtn.setOnClickListener {
                openSignUpFragment()
            }

//            registerBtn.setOnClickListener {
//
//
//            }


        }
    }

    private fun initViews() {
        binding.haventAccountBtn.colorizeEnd(
            getString(R.string.text_havent_account),
            getString(R.string.text_sign_up),
            getColor(requireContext(), R.color.grey_dark)
        )
        if(!authViewModel.isFirstOpen.get()){
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                binding.welcomeImg.visibility = View.INVISIBLE
                delay(100L)
                val transition = Slide(Gravity.END)
                transition.duration = 1000L
                TransitionManager.beginDelayedTransition(binding.rootLayout, transition)
                binding.welcomeImg.visibility = View.VISIBLE
                binding.welcomeImg.animate().rotationBy(-360f).setDuration(1000)
                    .setInterpolator(DecelerateInterpolator()).start()
                authViewModel.isFirstOpen.set(true)
            }
        }

    }

    private fun openSignUpFragment() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
    }

}