package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.core.Event
import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.usecase.auth.AuthUserUseCase
import com.denisbovsunivskyi.animetier.domain.usecase.validation.EmailValidation
import com.denisbovsunivskyi.animetier.domain.usecase.validation.PasswordValidation
import com.denisbovsunivskyi.animetier.presentation.model.auth.SignInModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUserUseCase: AuthUserUseCase,
    private val emailValidation: EmailValidation,
    private val passwordValidation: PasswordValidation
) :
    ViewModel() {
    val isFirstOpen: ObservableBoolean = ObservableBoolean(false)
    var signInModel: SignInModel = SignInModel()

    private val mAuthEventLiveData: MutableLiveData<Event<AuthActions>> =
        MutableLiveData<Event<AuthActions>>()
    var errorPassword: MutableLiveData<UniversalText> = MutableLiveData(UniversalText.Empty)
    var errorEmail: MutableLiveData<UniversalText> = MutableLiveData(UniversalText.Empty)

    fun getEventLiveData(): LiveData<Event<AuthActions>> {
        return mAuthEventLiveData
    }


    fun login() {
        if (!validateCredentials()) {
            viewModelScope.launch(Dispatchers.IO) {
                mAuthEventLiveData.postValue(Event(AuthActions.Loading))
                val email = signInModel.email.get() ?: ""
                val password = signInModel.password.get() ?: ""
                val result = authUserUseCase.execute(email, password)
                delay(5000)

                when (result) {
                    is ResponseState.Success -> mAuthEventLiveData.postValue(Event(AuthActions.Success.LoginSuccess))
                    is ResponseState.Error -> mAuthEventLiveData.postValue(
                        Event(AuthActions.Failed.LoginFailed(result.rawResponse))
                    )
                }
            }
        }
    }


    private fun validateCredentials(): Boolean {
        clearErrorMessages()
        val emailResult = emailValidation.execute(signInModel.email.get() ?: "")
        val passwordResult = passwordValidation.execute(signInModel.password.get() ?: "")

        val hasError = listOf(emailResult, passwordResult).any { it.errorMessage != null }
        if (hasError) {
            errorEmail.postValue(emailResult.errorMessage ?: UniversalText.Empty)
            errorPassword.postValue(passwordResult.errorMessage ?: UniversalText.Empty)
        }
        return hasError
    }

    private fun clearErrorMessages() {
        errorEmail.postValue(UniversalText.Empty)
        errorPassword.postValue(UniversalText.Empty)
    }

    fun clearModel() {
        signInModel = SignInModel()
    }
}

sealed class AuthActions {
    object Loading : AuthActions()
    sealed class Success : AuthActions() {
        object LoginSuccess : AuthActions()
    }

    sealed class Failed : AuthActions() {
        data class LoginFailed(val message: String) : AuthActions()
    }
}