package com.denisbovsunivskyi.animetier.data.datasource.user

import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun authUserWithEmail(
        email: String,
        password: String,
    ): Flow<ResponseState<FirebaseUser,String>>

    suspend fun registerUserWithEmail(
        model: FirebaseUser,
    ): Flow<ResponseState<FirebaseUser,String>>
}