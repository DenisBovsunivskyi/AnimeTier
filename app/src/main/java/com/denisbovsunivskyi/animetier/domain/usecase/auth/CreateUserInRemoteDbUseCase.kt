package com.denisbovsunivskyi.animetier.domain.usecase.auth

import com.denisbovsunivskyi.animetier.core.utils.validation.UniversalText
import com.denisbovsunivskyi.animetier.data.models.user.ResponseState
import com.denisbovsunivskyi.animetier.data.models.user.UserProfileModelDto
import com.denisbovsunivskyi.animetier.domain.repository.ProfileRepository
import com.denisbovsunivskyi.animetier.presentation.model.user.UserInfo

class CreateUserInRemoteDbUseCase(private val profileRepository: ProfileRepository) {
    suspend fun execute(userProfileModel: UserProfileModelDto): ResponseState<UserInfo, UniversalText> {
        return profileRepository.createUserInRemoteDb(userProfileModel)
    }
}