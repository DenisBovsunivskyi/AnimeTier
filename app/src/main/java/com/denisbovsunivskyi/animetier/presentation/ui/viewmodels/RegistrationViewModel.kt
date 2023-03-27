package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.usecase.auth.RegisterUserUseCase
import com.denisbovsunivskyi.animetier.presentation.model.auth.SignUpModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) :
    ViewModel() {
    var signUpModel: SignUpModel = SignUpModel()
    val registrationState: MutableLiveData<RegisterActions> = MutableLiveData()
    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = registerUserUseCase.execute(
                FirebaseUser(
                    null,
                    "tocia",
                    "Denis Bovsunivskyi",
                    "wweqtv1@gmail.com",
                    "123456789",
                    "123456789",
                    null
                )
            )

            when (result) {
                is ResponseState.Success -> Log.i("MYTAG", result.data.toString())
                is ResponseState.Error -> Log.i("MYTAG", result.rawResponse)
            }
        }
    }

    fun checkFirstStep() {

    }

    fun validateUser() {}
    fun clearModel() {
        signUpModel = SignUpModel()
    }
}

sealed class RegisterActions {
    sealed class Success : RegisterActions() {
        object FirstStepSuccess : Success()
    }

    sealed class Error : RegisterActions() {
        data class EmailIsNotCorrect(val message: String) : Error()
        data class PasswordIsToShort(val message: String) : Error()
        data class PasswordsNotMatch(val message: String) : Error()

    }

    object Loading : Success()

}