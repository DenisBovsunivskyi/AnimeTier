package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.core.Event
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.usecase.auth.RegisterUserUseCase
import com.denisbovsunivskyi.animetier.domain.usecase.validation.ConfirmPasswordValidation
import com.denisbovsunivskyi.animetier.domain.usecase.validation.EmailValidation
import com.denisbovsunivskyi.animetier.domain.usecase.validation.PasswordValidation
import com.denisbovsunivskyi.animetier.presentation.model.auth.SignUpModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val emailValidation: EmailValidation,
    private val passwordValidation: PasswordValidation,
    private val confirmPasswordValidation: ConfirmPasswordValidation
) :
    ViewModel() {
    var signUpModel: SignUpModel = SignUpModel()
    private val mRegistrationEventLiveData: MutableLiveData<Event<RegisterActions>> =
        MutableLiveData<Event<RegisterActions>>()

    fun getEventLiveData(): LiveData<Event<RegisterActions>> {
        return mRegistrationEventLiveData
    }

    var errorEmail: MutableLiveData<UniversalText> = MutableLiveData(UniversalText.Empty)
    var errorPassword: MutableLiveData<UniversalText> = MutableLiveData(UniversalText.Empty)
    var errorConfirmPassword: MutableLiveData<UniversalText> = MutableLiveData(UniversalText.Empty)
    fun register() {
        if (!validateRegistration()) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = registerUserUseCase.execute(
                    signUpModel.email.get() ?: "",
                    signUpModel.password.get() ?: ""
                )

                when (result) {
                    is ResponseState.Success -> mRegistrationEventLiveData.postValue(
                        Event(
                            RegisterActions.Success.RegistrationSuccess
                        )
                    )
                    is ResponseState.Error -> mRegistrationEventLiveData.postValue(
                        Event(
                            RegisterActions.Failed.RegistrationFailed(result.rawResponse)
                        )
                    )
                }
            }
        }
    }

    private fun validateRegistration(): Boolean {
        clearErrorMessages()
        val emailResult = emailValidation.execute(signUpModel.email.get() ?: "")
        val passwordResult = passwordValidation.execute(signUpModel.password.get() ?: "")
        val passwordConfirmResult = confirmPasswordValidation.execute(
            signUpModel.password.get() ?: "",
            signUpModel.confirmPassword.get() ?: ""
        )

        val hasError = listOf(
            emailResult,
            passwordResult,
            passwordConfirmResult
        ).any { it.errorMessage != null }
        if (hasError) {
            errorEmail.postValue(emailResult.errorMessage ?: UniversalText.Empty)
            errorPassword.postValue(passwordResult.errorMessage ?: UniversalText.Empty)
            errorConfirmPassword.postValue(
                passwordConfirmResult.errorMessage ?: UniversalText.Empty
            )
        }
        return hasError
    }

    private fun clearErrorMessages() {
        errorEmail.postValue(UniversalText.Empty)
        errorPassword.postValue(UniversalText.Empty)
        errorConfirmPassword.postValue(UniversalText.Empty)
    }

    fun clearModel() {
        signUpModel = SignUpModel()
    }
}

sealed class RegisterActions {
    object Loading : RegisterActions()
    sealed class Success : RegisterActions() {
        object RegistrationSuccess : RegisterActions()
    }

    sealed class Failed : RegisterActions() {
        data class RegistrationFailed(val message: String) : RegisterActions()
    }
}