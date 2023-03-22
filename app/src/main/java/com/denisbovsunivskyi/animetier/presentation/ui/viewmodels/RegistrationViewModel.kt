package com.denisbovsunivskyi.animetier.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.usecase.auth.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) :
    ViewModel() {
    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            registerUserUseCase.execute(
                FirebaseUser(
                    null,
                    "tocia",
                    "Denis Bovsunivskyi",
                    "wweqtv1@gmail.com",
                    "123456789",
                    "123456789",
                    null
                )
            ).onStart { }
                .collect{ result ->
                when(result){
                    is ResponseState.Success -> Log.i("MYTAG",result.data.toString())
                    is ResponseState.Error -> Log.i("MYTAG",result.rawResponse)
                }
            }
        }

    }
}