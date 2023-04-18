package com.denisbovsunivskyi.animetier.domain.usecase.auth

import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.data.models.user.UserProfileModelDto
import com.denisbovsunivskyi.animetier.domain.repository.ProfileRepository
import com.denisbovsunivskyi.animetier.presentation.model.user.UserInfo

class CreateUserInRemoteDbUseCase(private val profileRepository: ProfileRepository) {
    suspend fun execute(userProfileModel: UserProfileModelDto): ResponseState<UserInfo, String> {
        return profileRepository.createUserInRemoteDb(userProfileModel)
    }
}