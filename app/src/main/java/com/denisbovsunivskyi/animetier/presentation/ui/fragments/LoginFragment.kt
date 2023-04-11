package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.databinding.FragmentLoginBinding
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.AuthActions
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.LoginViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.colorizeEnd
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by activityViewModels<LoginViewModel>()

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
        initViewModels()

    }

    private fun initViewModels() {
        binding.model = loginViewModel.signInModel
       lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.STARTED){
               loginViewModel.loginStateFlow.collect{ state ->
                   when (state) {
                       is AuthActions.Success.LoginSuccess -> {
                           openHomeFragment()
                       }
                       is AuthActions.Failed.LoginFailed -> {
                           Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                       }
                       else -> {}
                   }
               }
           }

        }

    }


    private fun initListeners() {
        with(binding) {
            haventAccountBtn.setOnClickListener {
                openSignUpFragment()
            }
            loginBtn.setOnClickListener {
                loginViewModel.login()
                    //openHomeFragment()
            }
        }
    }

    private fun initViews() {
        binding.haventAccountBtn.colorizeEnd(
            getString(R.string.text_havent_account),
            getString(R.string.text_sign_up),
            getColor(requireContext(), R.color.grey_dark)
        )
        if (!loginViewModel.isFirstOpen.get()) {
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                delay(100L)
                val transition = Slide(Gravity.END)
                transition.duration = 1000L
                TransitionManager.beginDelayedTransition(binding.rootLayout, transition)
                binding.welcomeImg.visibility = View.VISIBLE
                binding.welcomeImg.animate().rotationBy(-360f).setDuration(1000)
                    .setInterpolator(DecelerateInterpolator()).start()
                loginViewModel.isFirstOpen.set(true)
            }
        }else{
            binding.welcomeImg.visibility = View.VISIBLE
        }

    }

    private fun openSignUpFragment() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
    }
    private fun openHomeFragment() {
        findNavController().navigate(LoginFragmentDirections.actionGlobalHomeList())
    }


}