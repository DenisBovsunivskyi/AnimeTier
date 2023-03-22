package com.denisbovsunivskyi.animetier.domain.usecase.auth

import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository

class AuthUserUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String) {
        authRepository.authUserByEmail(email, password)
    }
}