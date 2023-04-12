package com.denisbovsunivskyi.animetier.data.datasource.user

import com.denisbovsunivskyi.animetier.data.models.user.ResponseState

interface AuthDataSource {
    suspend fun authUserWithEmail(
        email: String,
        password: String,
    ): ResponseState<String, String>

    suspend fun registerUserWithEmail(
        email: String,
        password: String
    ): ResponseState<String, String>
}