package com.denisbovsunivskyi.animetier.data.repository.user

import com.denisbovsunivskyi.animetier.data.datasource.user.AuthDataSource
import com.denisbovsunivskyi.animetier.data.datasource.user.UpdateUserDataSource
import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val userDataSource: UpdateUserDataSource
) : AuthRepository {
    override suspend fun authUserByEmail(
        email: String,
        password: String,
    ): ResponseState<String, String> {
        return authDataSource.authUserWithEmail(email, password)
    }

    override suspend fun registerUserByEmail(model: FirebaseUser): ResponseState<FirebaseUser, String> {
        val response = authDataSource.registerUserWithEmail(model)
        return when (response) {
            is ResponseState.Success -> {
                userDataSource.updateUserWhileRegister(response.data)
            }
            is ResponseState.Error -> {
                response
            }
        }
    }
}
