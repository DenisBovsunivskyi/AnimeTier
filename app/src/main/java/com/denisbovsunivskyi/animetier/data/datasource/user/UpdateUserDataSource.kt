package com.denisbovsunivskyi.animetier.data.datasource.user

import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState

interface UpdateUserDataSource {
    suspend fun updateUserWhileRegister(user: FirebaseUser): ResponseState<FirebaseUser, String>
}