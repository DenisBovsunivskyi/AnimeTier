package com.denisbovsunivskyi.animetier.domain.repository

import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState


interface AuthRepository {
    suspend fun authUserByEmail(
        email: String,
        password: String,
    ): ResponseState<String, String>

    suspend fun registerUserByEmail(model: FirebaseUser): ResponseState<FirebaseUser, String>
}