package com.denisbovsunivskyi.animetier.data.repository.user

import com.denisbovsunivskyi.animetier.data.datasource.user.AuthDataSource
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun authUserByEmail(
        email: String,
        password: String,
    ): ResponseState<String, String> {
        return authDataSource.authUserWithEmail(email, password)
    }

    override suspend fun registerUserByEmail(
        email: String,
        password: String
    ): ResponseState<String, String> {
        return authDataSource.registerUserWithEmail(email, password)
    }
}
