package com.denisbovsunivskyi.animetier.domain.usecase.auth

import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.auth.AuthRepository

class RegisterUserUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): ResponseState<String, String> {
        return authRepository.registerUserByEmail(email, password)
    }
}