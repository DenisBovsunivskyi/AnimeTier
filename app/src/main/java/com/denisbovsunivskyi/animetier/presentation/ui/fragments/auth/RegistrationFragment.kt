package com.denisbovsunivskyi.animetier.presentation.ui.fragments.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.databinding.FragmentRegistrationBinding
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.auth.RegisterActions
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.auth.RegistrationViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.clearError
import com.denisbovsunivskyi.animetier.presentation.utils.setErrorMsg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : BaseBindingFragment<FragmentRegistrationBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegistrationBinding
        get() = FragmentRegistrationBinding::inflate
    private val registrationViewModel by activityViewModels<RegistrationViewModel>()

    override fun init() {
        registrationViewModel.clearModel()
        initClearFocusInputs()
        observeValidation()
    }


    override fun initListeners() {
        with(binding) {
            registerBtn.setOnClickListener {
                registrationViewModel.register()
            }
            textHaveAccountBtn.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
    }

    private fun initClearFocusInputs() {
        binding.editEmail.setTargetForCleanFocus(binding.inputEmail)
        binding.editEmail.setNextTargetView(binding.editPassword)
        binding.editPassword.setTargetForCleanFocus(binding.inputPassword)
        binding.editPassword.setNextTargetView(binding.editConfirmPassword)
        binding.editConfirmPassword.setTargetForCleanFocus(binding.inputConfirmPassword)

    }

    override fun initViewModels() {
        binding.model = registrationViewModel.signUpModel
        registrationViewModel.getEventLiveData().observe(viewLifecycleOwner) { state ->
            val event = state.contentIfNotHandled
            event?.let {
                when (event) {
                    is RegisterActions.Success.RegistrationSuccess -> {
                        binding.registerBtn.isEnabled = true
                        openRegistrationProfile()
                    }
                    is RegisterActions.Failed.RegistrationFailed -> {
                        binding.registerBtn.isEnabled = true
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }
                    is RegisterActions.Loading -> {
                        binding.registerBtn.isEnabled = false
                    }
                }
            }
        }
    }

    private fun observeValidation() {
        registrationViewModel.errorEmail.observe(viewLifecycleOwner) {
            if (it is UniversalText.Empty) {
                binding.inputEmail.clearError()
            } else {
                binding.inputEmail.setErrorMsg(it.asString(requireContext()))
            }
        }
        registrationViewModel.errorPassword.observe(viewLifecycleOwner) {
            if (it is UniversalText.Empty) {
                binding.inputPassword.clearError()
            } else {
                binding.inputPassword.setErrorMsg(it.asString(requireContext()))
            }
        }
        registrationViewModel.errorConfirmPassword.observe(viewLifecycleOwner) {
            if (it is UniversalText.Empty) {
                binding.inputConfirmPassword.clearError()
            } else {
                binding.inputConfirmPassword.setErrorMsg(it.asString(requireContext()))
            }
        }
    }

    private fun openRegistrationProfile() {
        findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToRegistrationProfileInfoFragment())
    }

}