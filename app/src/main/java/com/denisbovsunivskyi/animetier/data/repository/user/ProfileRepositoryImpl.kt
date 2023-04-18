package com.denisbovsunivskyi.animetier.data.repository.user

import com.denisbovsunivskyi.animetier.data.datasource.user.UserProfileDataSource
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.presentation.model.user.UserInfo
import com.denisbovsunivskyi.animetier.data.models.user.UserProfileModelDto
import com.denisbovsunivskyi.animetier.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val userProfileDataSource: UserProfileDataSource
) : ProfileRepository {

    override suspend fun createUserInRemoteDb(model: UserProfileModelDto): ResponseState<UserInfo, String> {
          return userProfileDataSource.createUserWhileRegister(model)
    }
}
