package com.denisbovsunivskyi.animetier.domain.usecase.auth

import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository


class AuthUserUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): ResponseState<String, String> {
      return authRepository.authUserByEmail(email, password)
    }
}