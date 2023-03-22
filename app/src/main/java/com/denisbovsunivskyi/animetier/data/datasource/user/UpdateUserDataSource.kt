package com.denisbovsunivskyi.animetier.data.datasource.user

import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.data.models.user.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UpdateUserDataSource {
   suspend fun updateUserWhileRegister(user: FirebaseUser): Flow<ResponseState<FirebaseUser,String>>
}