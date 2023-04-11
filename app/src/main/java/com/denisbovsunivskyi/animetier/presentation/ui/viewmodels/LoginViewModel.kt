package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.usecase.auth.AuthUserUseCase
import com.denisbovsunivskyi.animetier.presentation.model.auth.SignInModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUserUseCase: AuthUserUseCase
) :
    ViewModel() {
    val isFirstOpen:ObservableBoolean = ObservableBoolean(false)
    var signInModel: SignInModel = SignInModel()
    private val _loginStateFlow = MutableStateFlow<AuthActions>(AuthActions.Loading)
    val loginStateFlow: SharedFlow<AuthActions> = _loginStateFlow
    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = signInModel.email.get() ?: ""
            val password = signInModel.password.get() ?: ""
            val result = authUserUseCase.execute(email,password)

            when (result) {
                is ResponseState.Success -> _loginStateFlow.value = (AuthActions.Success.LoginSuccess)
                is ResponseState.Error ->_loginStateFlow.value = (AuthActions.Failed.LoginFailed(result.rawResponse))
            }
        }
    }

    fun validateCredantials() {}
    fun clearModel() {
        signInModel = SignInModel()
    }
}
sealed class AuthActions {
    object Loading:AuthActions()
    sealed class Success : AuthActions(){
        object LoginSuccess: AuthActions()
    }

    sealed  class Failed : AuthActions() {
        data class LoginFailed(val message: String) : AuthActions()
    }
}