package com.denisbovsunivskyi.animetier.presentation.ui.fragments.auth

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.databinding.FragmentLoginBinding
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.auth.AuthActions
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.auth.LoginViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.clearError
import com.denisbovsunivskyi.animetier.presentation.utils.colorizeEnd
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.setErrorMsg
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.showView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseBindingFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    private val loginViewModel by activityViewModels<LoginViewModel>()

    override fun init() {
        initClearFocusInputs()
        observeValidation()
    }

    override fun initViewModels() {
        binding.model = loginViewModel.signInModel


        loginViewModel.getEventLiveData().observe(viewLifecycleOwner) { state ->
            val event = state.contentIfNotHandled
            event?.let {
                when (event) {
                    is AuthActions.Success.LoginSuccess -> {
                        binding.loginBtn.isEnabled = true
                        openHomeFragment()
                    }

                    is AuthActions.Failed.LoginFailed -> {
                        binding.loginBtn.isEnabled = true
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }

                    is AuthActions.Loading -> {
                        binding.loginBtn.isEnabled = false
                    }
                }
            }
        }
    }

    override fun initViews() {
        binding.haventAccountBtn.colorizeEnd(
            getString(R.string.text_havent_account),
            getString(R.string.text_sign_up),
            getColor(requireContext(), R.color.grey_dark)
        )
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                if (!loginViewModel.isFirstOpen.get()) {
                    delay(100L)
                    val transition = Slide(Gravity.END)
                    transition.duration = 1000L
                    TransitionManager.beginDelayedTransition(binding.rootLayout, transition)
                    binding.welcomeImg.visibility = View.VISIBLE
                    binding.welcomeImg.animate().rotationBy(-360f).setDuration(1000)
                        .setInterpolator(DecelerateInterpolator()).start()
                    loginViewModel.isFirstOpen.set(true)
                } else {
                    binding.welcomeImg.showView()
                }
            }
        }
    }


    override fun initListeners() {
        with(binding) {
            haventAccountBtn.setOnClickListener {
                openSignUpFragment()
            }
            loginBtn.setOnClickListener {
                loginViewModel.login()
            }
        }
    }

    private fun observeValidation() {
        loginViewModel.errorEmail.observe(viewLifecycleOwner) {
            if (it is UniversalText.Empty) {
                binding.inputEmail.clearError()
            } else {
                binding.inputEmail.setErrorMsg(it.asString(requireContext()))
            }
        }
        loginViewModel.errorPassword.observe(viewLifecycleOwner) {
            if (it is UniversalText.Empty) {
                binding.inputPassword.clearError()
            } else {
                binding.inputPassword.setErrorMsg(it.asString(requireContext()))
            }
        }
    }

    private fun initClearFocusInputs() {
        binding.editEmail.setTargetForCleanFocus(binding.inputEmail)
        binding.editEmail.setNextTargetView(binding.editPassword)
        binding.editPassword.setTargetForCleanFocus(binding.inputPassword)
    }

    private fun openSignUpFragment() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
    }

    private fun openHomeFragment() {
        findNavController().navigate(LoginFragmentDirections.actionGlobalHomeList())
    }


}