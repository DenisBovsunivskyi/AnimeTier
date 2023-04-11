package com.denisbovsunivskyi.animetier.domain.usecase.auth

import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository

class RegisterUserUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(model: FirebaseUser): ResponseState<FirebaseUser, String> {
        return authRepository.registerUserByEmail(model)
    }
}