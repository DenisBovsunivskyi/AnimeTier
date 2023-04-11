package com.denisbovsunivskyi.animetier.data.datasource.user

import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState

interface AuthDataSource {
    suspend fun authUserWithEmail(
        email: String,
        password: String,
    ): ResponseState<String, String>

    suspend fun registerUserWithEmail(
        user: FirebaseUser,
    ): ResponseState<FirebaseUser, String>
}