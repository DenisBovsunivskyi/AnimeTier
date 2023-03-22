package com.denisbovsunivskyi.animetier.domain.usecase.auth

import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class RegisterUserUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(model: FirebaseUser): Flow<ResponseState<FirebaseUser,String>> {
        return authRepository.registerUserByEmail(model)
    }
}