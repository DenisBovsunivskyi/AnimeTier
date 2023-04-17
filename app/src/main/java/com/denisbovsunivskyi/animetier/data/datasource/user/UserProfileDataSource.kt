package com.denisbovsunivskyi.animetier.data.datasource.user

import com.denisbovsunivskyi.animetier.data.models.user.UserProfileModelDto
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.presentation.model.user.UserInfo

interface UserProfileDataSource {
    suspend fun createUserWhileRegister(user: UserProfileModelDto): ResponseState<UserInfo, String>
}