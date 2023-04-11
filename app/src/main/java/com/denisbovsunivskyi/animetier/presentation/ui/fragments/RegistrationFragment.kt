package com.denisbovsunivskyi.animetier.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.denisbovsunivskyi.animetier.R
import com.denisbovsunivskyi.animetier.databinding.FragmentRegistrationBinding
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.RegisterActions
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.RegistrationViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.setErrorMsg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel by activityViewModels<RegistrationViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
        viewModel.clearModel()
        initListeners()
        initViewModels()
        initClearFocusInputs()
    }


    private fun initListeners() {
        with(binding) {
            registerBtn.setOnClickListener {
                viewModel.register()
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

    private fun initViewModels() {
        viewModel.registrationState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RegisterActions.Error.EmailIsNotCorrect -> {
                    binding.inputEmail.setErrorMsg(state.message)
                }
                is RegisterActions.Error.PasswordIsToShort -> {
                    binding.inputPassword.setErrorMsg(state.message)
                }
                is RegisterActions.Error.PasswordsNotMatch -> {
                    binding.inputPassword.setErrorMsg(state.message)
                    binding.inputConfirmPassword.setErrorMsg(state.message)
                }
                is RegisterActions.Success.FirstStepSuccess -> {
                    //todo
                }
                is RegisterActions.Loading -> {

                }
            }
        }
    }

}