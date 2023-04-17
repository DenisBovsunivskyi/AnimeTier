package com.denisbovsunivskyi.animetier.domain.repository

import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.presentation.model.user.UserInfo
import com.denisbovsunivskyi.animetier.data.models.user.UserProfileModelDto


interface ProfileRepository {
    suspend fun createUserWhileRegister(model:UserProfileModelDto): ResponseState<UserInfo, String>
}