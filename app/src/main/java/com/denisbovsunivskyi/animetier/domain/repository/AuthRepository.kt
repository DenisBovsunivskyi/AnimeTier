package com.denisbovsunivskyi.animetier.domain.repository

import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    suspend fun authUserByEmail(
        email: String,
        password: String,
    ): Flow<ResponseState<FirebaseUser,String>>

    suspend fun registerUserByEmail(model: FirebaseUser): ResponseState<FirebaseUser,String>
}